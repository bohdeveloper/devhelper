package com.devhelper.service;

import com.devhelper.entity.OracleObject;
import com.devhelper.repository.OracleObjectRepository;
import com.devhelper.storage.StorageService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OracleToolkitService {

    private final OracleObjectRepository repository;
    private final StorageService storageService;

    public OracleToolkitService(@NonNull OracleObjectRepository repository, @NonNull StorageService storageService) {
        this.repository = repository;
        this.storageService = storageService;
    }

    public List<OracleObject> findAll() {
        return repository.findAll();
    }

    @NonNull
    public Optional<OracleObject> findById(@NonNull Long id) {
        return repository.findById(id);
    }

    public OracleObject save(OracleObject oracleObject) {
        // En cada guardado/actualización del UI, volcamos a fichero físico (.sql) en la carpeta oracle-toolkit/
        String filename = oracleObject.getName().toUpperCase() + "_" + oracleObject.getType().toUpperCase() + ".sql";
        storageService.saveOracleScript(filename, oracleObject.getDefinition());
        
        return repository.save(oracleObject);
    }

    public void deleteById(@NonNull Long id) {
        repository.deleteById(id);
    }
}
