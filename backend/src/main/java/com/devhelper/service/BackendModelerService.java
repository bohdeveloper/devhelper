package com.devhelper.service;

import com.devhelper.entity.GenEntity;
import com.devhelper.repository.GenEntityFieldRepository;
import com.devhelper.repository.GenEntityRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BackendModelerService {

    private final GenEntityRepository entityRepository;
    @SuppressWarnings("unused")
    private final GenEntityFieldRepository fieldRepository;

    public BackendModelerService(@NonNull GenEntityRepository entityRepository, @NonNull GenEntityFieldRepository fieldRepository) {
        this.entityRepository = entityRepository;
        this.fieldRepository = fieldRepository;
    }

    @NonNull
    public List<GenEntity> findAllEntities() {
        return entityRepository.findAll();
    }

    @NonNull
    public Optional<GenEntity> findEntityById(@NonNull Long id) {
        return entityRepository.findById(id);
    }
    
    // Al guardar manejamos el mappedBy seteando la entidad a los hijos
    @NonNull
    public GenEntity saveEntity(@NonNull GenEntity entity) {
        if (entity.getFields() != null) {
            entity.getFields().forEach(f -> f.setEntity(entity));
        }
        return entityRepository.save(entity);
    }

    public void deleteEntity(@NonNull Long id) {
        entityRepository.deleteById(id);
    }
}
