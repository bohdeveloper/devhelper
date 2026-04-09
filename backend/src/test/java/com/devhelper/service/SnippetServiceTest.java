package com.devhelper.service;

import com.devhelper.entity.Snippet;
import com.devhelper.repository.SnippetRepository;
import com.devhelper.storage.StorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SnippetServiceTest {

    @Mock
    private SnippetRepository repository;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SnippetService snippetService;

    @NonNull
    private Snippet mockSnippet = new Snippet();

    @BeforeEach
    void setUp() {
        mockSnippet = new Snippet();
        mockSnippet.setId(1L);
        mockSnippet.setName("Test Snippet");
        mockSnippet.setLanguage("java");
        mockSnippet.setContent("System.out.println(\"Hello\");");
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(mockSnippet));
        List<Snippet> results = snippetService.findAll();
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertEquals("Test Snippet", results.get(0).getName());
    }

    @Test
    void testFindById_Found() {
        when(repository.findById(1L)).thenReturn(Optional.of(mockSnippet));
        Optional<Snippet> result = snippetService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("java", result.get().getLanguage());
    }

    @Test
    void testFindById_NotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        Optional<Snippet> result = snippetService.findById(99L);
        assertFalse(result.isPresent());
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(repository.save(any(Snippet.class))).thenReturn(mockSnippet);

        Snippet result = snippetService.save(mockSnippet);

        assertNotNull(result);
        assertEquals("Test Snippet", result.getName());
        verify(repository, times(1)).save(mockSnippet);
    }

    @Test
    void testDeleteById() {
        doNothing().when(repository).deleteById(1L);
        snippetService.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
