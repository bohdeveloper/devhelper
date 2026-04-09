package org.example.generated.controller;

import org.example.generated.entity.${entity.name};
import org.example.generated.service.${entity.name}Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

<#assign pkType="Long">
<#list fields as f><#if f.primaryKey><#assign pkType=f.type></#if></#list>

@WebMvcTest(${entity.name}Controller.class)
public class ${entity.name}ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ${entity.name}Service service;

    private ${entity.name} mockEntity;

    @BeforeEach
    void setUp() {
        mockEntity = new ${entity.name}();
        // TODO: Inicializar propiedades del mockEntity aquí
    }

    @Test
    void testGetAll() throws Exception {
        Mockito.when(service.findAll()).thenReturn(Arrays.asList(mockEntity));

        mockMvc.perform(get("/api/${entity.name?lower_case}s"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetById_Found() throws Exception {
        <#if pkType == "Long">
        ${pkType} id = 1L;
        <#elseif pkType == "Integer">
        ${pkType} id = 1;
        <#elseif pkType == "String">
        ${pkType} id = "123";
        <#else>
        ${pkType} id = null; // Asignar valor
        </#if>
        Mockito.when(service.findById(id)).thenReturn(Optional.of(mockEntity));

        mockMvc.perform(get("/api/${entity.name?lower_case}s/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetById_NotFound() throws Exception {
        <#if pkType == "Long">
        ${pkType} id = 99L;
        <#elseif pkType == "Integer">
        ${pkType} id = 99;
        <#elseif pkType == "String">
        ${pkType} id = "999";
        <#else>
        ${pkType} id = null; // Asignar valor
        </#if>
        Mockito.when(service.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/${entity.name?lower_case}s/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreate() throws Exception {
        Mockito.when(service.save(any(${entity.name}.class))).thenReturn(mockEntity);

        mockMvc.perform(post("/api/${entity.name?lower_case}s")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")) // TODO: Provide real JSON content
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate_Found() throws Exception {
        <#if pkType == "Long">
        ${pkType} id = 1L;
        <#elseif pkType == "Integer">
        ${pkType} id = 1;
        <#elseif pkType == "String">
        ${pkType} id = "123";
        <#else>
        ${pkType} id = null; // Asignar valor
        </#if>
        Mockito.when(service.findById(id)).thenReturn(Optional.of(mockEntity));
        Mockito.when(service.save(any(${entity.name}.class))).thenReturn(mockEntity);

        mockMvc.perform(put("/api/${entity.name?lower_case}s/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")) // // TODO: Provide real JSON content
                .andExpect(status().isOk());
    }

    @Test
    void testDelete_Found() throws Exception {
        <#if pkType == "Long">
        ${pkType} id = 1L;
        <#elseif pkType == "Integer">
        ${pkType} id = 1;
        <#elseif pkType == "String">
        ${pkType} id = "123";
        <#else>
        ${pkType} id = null; // Asignar valor
        </#if>
        Mockito.when(service.findById(id)).thenReturn(Optional.of(mockEntity));

        mockMvc.perform(delete("/api/${entity.name?lower_case}s/{id}", id))
                .andExpect(status().isOk());
    }
}
