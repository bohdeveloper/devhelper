package org.example.generated.service;

import org.example.generated.entity.${entity.name};
import org.example.generated.repository.${entity.name}Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ${entity.name}ServiceTest {

    @Mock
    private ${entity.name}Repository repository;

    @InjectMocks
    private ${entity.name}Service service;

    private ${entity.name} mockEntity;

    <#assign pkType="Long">
    <#list fields as f><#if f.primaryKey><#assign pkType=f.type></#if></#list>

    @BeforeEach
    void setUp() {
        mockEntity = new ${entity.name}();
        // TODO: Inicializar propiedades del mockEntity aquí
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(mockEntity));
        List<${entity.name}> results = service.findAll();
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById_Found() {
        <#if pkType == "Long">
        ${pkType} id = 1L;
        <#elseif pkType == "Integer">
        ${pkType} id = 1;
        <#elseif pkType == "String">
        ${pkType} id = "123";
        <#else>
        ${pkType} id = null; // TODO: Asignar valor apropiado
        </#if>
        when(repository.findById(id)).thenReturn(Optional.of(mockEntity));
        Optional<${entity.name}> result = service.findById(id);
        assertTrue(result.isPresent());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void testFindById_NotFound() {
        <#if pkType == "Long">
        ${pkType} id = 99L;
        <#elseif pkType == "Integer">
        ${pkType} id = 99;
        <#elseif pkType == "String">
        ${pkType} id = "999";
        <#else>
        ${pkType} id = null; // TODO: Asignar valor apropiado
        </#if>
        when(repository.findById(id)).thenReturn(Optional.empty());
        Optional<${entity.name}> result = service.findById(id);
        assertFalse(result.isPresent());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void testSave() {
        when(repository.save(any(${entity.name}.class))).thenReturn(mockEntity);
        ${entity.name} result = service.save(mockEntity);
        assertNotNull(result);
        verify(repository, times(1)).save(mockEntity);
    }

    @Test
    void testDeleteById() {
        <#if pkType == "Long">
        ${pkType} id = 1L;
        <#elseif pkType == "Integer">
        ${pkType} id = 1;
        <#elseif pkType == "String">
        ${pkType} id = "123";
        <#else>
        ${pkType} id = null; // TODO: Asignar valor apropiado
        </#if>
        doNothing().when(repository).deleteById(id);
        service.deleteById(id);
        verify(repository, times(1)).deleteById(id);
    }
}
