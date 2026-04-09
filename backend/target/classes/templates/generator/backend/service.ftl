package org.example.generated.service;

import org.example.generated.entity.${entity.name};
import org.example.generated.repository.${entity.name}Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ${entity.name}Service {

    private final ${entity.name}Repository repository;

    public ${entity.name}Service(${entity.name}Repository repository) {
        this.repository = repository;
    }

    public List<${entity.name}> findAll() {
        return repository.findAll();
    }

    public Optional<${entity.name}> findById(<#list fields as f><#if f.primaryKey>${f.type}</#if></#list> id) {
        return repository.findById(id);
    }

    public ${entity.name} save(${entity.name} entity) {
        return repository.save(entity);
    }

    public void deleteById(<#list fields as f><#if f.primaryKey>${f.type}</#if></#list> id) {
        repository.deleteById(id);
    }
}
