package com.devhelper.controller;

import com.devhelper.entity.Snippet;
import com.devhelper.service.SnippetService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SnippetController.class)
public class SnippetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SnippetService snippetService;

    @Test
    @WithMockUser(username = "admin")
    void testIndexPage() throws Exception {
        Snippet snippet = new Snippet();
        snippet.setName("HTML Boilerplate");
        
        Mockito.when(snippetService.findAll()).thenReturn(Arrays.asList(snippet));

        mockMvc.perform(get("/snippets"))
                .andExpect(status().isOk())
                .andExpect(view().name("snippets/index"))
                .andExpect(model().attributeExists("snippets"))
                // the helper advice adds the current URI for nav highlighting
                .andExpect(model().attribute("currentUri", "/snippets"));
    }

    @Test
    @WithMockUser(username = "admin")
    void testCreateForm() throws Exception {
        mockMvc.perform(get("/snippets/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("snippets/form"))
                .andExpect(model().attributeExists("snippet"))
                .andExpect(model().attribute("currentUri", "/snippets/create"));
    }

    @Test
    @WithMockUser(username = "admin")
    @SuppressWarnings("null")
    void testSaveSnippet() throws Exception {
        Snippet snippet = new Snippet();
        snippet.setName("New");
        
        Mockito.when(snippetService.save(any(Snippet.class))).thenReturn(snippet);

        mockMvc.perform(post("/snippets/save")
                .with(csrf()) // Necesario si CSRF estuviese activo, aunque está deshabilitado globalmente en SecurityConfig. Lo enviamos por seguridad.
                /* suppress null-safety warning around mock CSRF */
                .param("name", "New")
                .param("language", "java")
                .param("content", "class A{}"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/snippets"));
    }
    
    @Test
    void testAccessDeniedForUnauthenticatedUsers() throws Exception {
        mockMvc.perform(get("/snippets"))
                .andExpect(status().isUnauthorized()); 
                // Spring security re-envía al log-in o escupe un 401. Como usamos HTTP Basic en nuestra UI, devuelve 401.
    }
}
