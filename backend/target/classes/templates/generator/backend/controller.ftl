package org.example.generated.controller;

import org.example.generated.entity.${entity.name};
import org.example.generated.service.${entity.name}Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/${entity.name?lower_case}s")
public class ${entity.name}Controller {

    private final ${entity.name}Service service;

    public ${entity.name}Controller(${entity.name}Service service) {
        this.service = service;
    }

    @GetMapping
    public List<${entity.name}> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<${entity.name}> getById(@PathVariable <#list fields as f><#if f.primaryKey>${f.type}</#if></#list> id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ${entity.name} create(@RequestBody ${entity.name} entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<${entity.name}> update(@PathVariable <#list fields as f><#if f.primaryKey>${f.type}</#if></#list> id, @RequestBody ${entity.name} entity) {
        return service.findById(id)
                .map(existing -> {
                    // Actualizar campos aca si no se reemplaza objeto completo... por simplicidad de DTO guardamos el mergeado
<#list fields as f>
    <#if !f.primaryKey>
                    existing.set${f.name?cap_first}(entity.get${f.name?cap_first}());
    </#if>
</#list>
                    return ResponseEntity.ok(service.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable <#list fields as f><#if f.primaryKey>${f.type}</#if></#list> id) {
        return service.findById(id)
                .map(existing -> {
                    service.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
