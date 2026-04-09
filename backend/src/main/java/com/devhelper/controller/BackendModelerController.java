package com.devhelper.controller;

import com.devhelper.entity.GenEntity;
import com.devhelper.entity.GenEntityField;
import com.devhelper.generator.TemplateEngineService;
import com.devhelper.service.BackendModelerService;
import com.devhelper.storage.StorageService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/backend-models")
public class BackendModelerController {

    private final BackendModelerService modelerService;
    private final TemplateEngineService templateEngine;
    private final StorageService storageService;

    public BackendModelerController(BackendModelerService modelerService, TemplateEngineService templateEngine, StorageService storageService) {
        this.modelerService = modelerService;
        this.templateEngine = templateEngine;
        this.storageService = storageService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("entities", modelerService.findAllEntities());
        return "backend/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        GenEntity entity = new GenEntity();
        
        // Agregar id por defecto para comodidad visual
        GenEntityField idField = new GenEntityField();
        idField.setName("id");
        idField.setType("Long");
        idField.setPrimaryKey(true);
        idField.setNullable(false);
        entity.getFields().add(idField);
        
        model.addAttribute("entityModel", entity);
        return "backend/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable @NonNull Long id, Model model) {
        return modelerService.findEntityById(id).map(entity -> {
            model.addAttribute("entityModel", entity);
            return "backend/form";
        }).orElse("redirect:/backend-models");
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("entityModel") GenEntity entity, RedirectAttributes redirectAttributes) {
        // Limpiamos rows vacios que vengan del front list
        entity.getFields().removeIf(f -> f.getName() == null || f.getName().trim().isEmpty());
        
        modelerService.saveEntity(entity);
        redirectAttributes.addFlashAttribute("successMessage", "Modelo backend guardado");
        return "redirect:/backend-models";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable @NonNull Long id, RedirectAttributes redirectAttributes) {
        modelerService.deleteEntity(id);
        redirectAttributes.addFlashAttribute("successMessage", "Modelo eliminado");
        return "redirect:/backend-models";
    }

    @GetMapping("/generate/{id}")
    public void generateCode(@PathVariable @NonNull Long id, @RequestParam("type") String type, HttpServletResponse response) throws IOException {
        Optional<GenEntity> optEntity = modelerService.findEntityById(id);
        if (optEntity.isEmpty()) {
            response.sendError(404, "Entidad no encontrada");
            return;
        }

        GenEntity entity = optEntity.get();
        Map<String, Object> params = new HashMap<>();
        params.put("entity", entity);
        params.put("fields", entity.getFields());
        params.put("entityName", entity.getName());
        params.put("entityNameLower", entity.getName().toLowerCase());

        String templateFile = "backend/" + type + ".ftl"; // Ej: backend/entity.ftl
        String generatedCode = templateEngine.generateFromTemplate(templateFile, params);

        // También persistimos el file generado en local
        String filename = entity.getName() + getSuffixForType(type);
        storageService.saveGeneratorResult(filename, generatedCode);

        // Forzamos descarga en el navegador
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        
        try (PrintWriter writer = response.getWriter()) {
            writer.write(generatedCode);
        }
    }

    private String getSuffixForType(String type) {
        return switch (type) {
            case "entity" -> ".java";
            case "repository" -> "Repository.java";
            case "service" -> "Service.java";
            case "controller" -> "Controller.java";
            case "dto" -> "DTO.java";
            case "service_test" -> "ServiceTest.java";
            case "controller_test" -> "ControllerTest.java";
            default -> ".txt";
        };
    }
}
