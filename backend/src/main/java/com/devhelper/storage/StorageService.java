package com.devhelper.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class StorageService {

    // Se asume que app.storage.base se define en application.properties o toma user.dir por defecto
    @Value("${app.storage.base:${user.dir}/storage}")
    private String baseStoragePath;

    private Path rootLocation;
    private Path SnippetsLocation;
    private Path generatorsLocation;
    private Path oracleLocation;

    @PostConstruct
    public void init() {
        try {
            this.rootLocation = Paths.get(baseStoragePath);
            this.SnippetsLocation = this.rootLocation.resolve("Snippets");
            this.generatorsLocation = this.rootLocation.resolve("generators");
            this.oracleLocation = this.rootLocation.resolve("oracle");

            Files.createDirectories(this.rootLocation);
            Files.createDirectories(this.SnippetsLocation);
            Files.createDirectories(this.generatorsLocation);
            Files.createDirectories(this.oracleLocation);
            
            // Revisa también la de base de datos sqlite que debería estar en el home para asegurar que exista
            String userHome = System.getProperty("user.home");
            Path dbPath = Paths.get(userHome, ".devhelper", "db");
            Files.createDirectories(dbPath);
            Path logsPath = Paths.get(userHome, ".devhelper", "logs");
            Files.createDirectories(logsPath);

        } catch (IOException e) {
            throw new RuntimeException("No se pudieron inicializar las carpetas de almacenamiento local", e);
        }
    }

    public void saveSnippet(String filename, String content) {
        saveFile(this.SnippetsLocation, filename, content);
    }

    public void saveOracleScript(String filename, String content) {
        saveFile(this.oracleLocation, filename, content);
    }

    public void saveGeneratorResult(String filename, String content) {
        saveFile(this.generatorsLocation, filename, content);
    }

    private void saveFile(Path location, String filename, String content) {
        try {
            Path targetFile = location.resolve(filename).normalize();
            if (!targetFile.getParent().equals(location)) {
                throw new SecurityException("No se puede almacenar el archivo fuera del directorio designado");
            }
            Files.writeString(targetFile, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Atencion: Fallo al guardar archivo " + filename, e);
        }
    }
    
    public String readFile(Path location, String filename) {
        try {
            Path targetFile = location.resolve(filename).normalize();
            if (!targetFile.getParent().equals(location)) {
                throw new SecurityException("No se puede leer el archivo fuera del directorio designado");
            }
            return Files.readString(targetFile);
        } catch (IOException e) {
            throw new RuntimeException("Atencion: Fallo al leer archivo " + filename, e);
        }
    }
}
