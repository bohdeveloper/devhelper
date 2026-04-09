package com.devhelper.controller;

import com.devhelper.generator.TemplateEngineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/frontend")
public class FrontendGeneratorController {

    private final TemplateEngineService templateEngine;

    public FrontendGeneratorController(TemplateEngineService templateEngine) {
        this.templateEngine = templateEngine;
    }

    @GetMapping
    public String index() {
        return "frontend/index";
    }

    @PostMapping("/generate/crud-form")
    public String generateCrudForm(
            @RequestParam("entityName") String entityName,
            @RequestParam("fields") String fieldsRaw,
            Model model) {
        
        List<String> fieldList = Arrays.stream(fieldsRaw.split(","))
                                     .map(String::trim)
                                     .filter(s -> !s.isEmpty())
                                     .collect(Collectors.toList());

        Map<String, Object> params = new HashMap<>();
        params.put("entityName", entityName);
        params.put("fields", fieldList);

        String generatedHtml = templateEngine.generateFromTemplate("frontend/bootstrap_crud_form.ftl", params);
        
        model.addAttribute("title", "Formulario CRUD Bootstrap: " + entityName);
        model.addAttribute("generatedCode", generatedHtml);
        model.addAttribute("language", "html");
        
        return "frontend/result";
    }

    @PostMapping("/generate/datatables")
    public String generateDataTables(
            @RequestParam("entityName") String entityName,
            @RequestParam("columns") String columnsRaw,
            @RequestParam("endpoint") String endpoint,
            Model model) {
        
        List<String> columnList = Arrays.stream(columnsRaw.split(","))
                                     .map(String::trim)
                                     .filter(s -> !s.isEmpty())
                                     .collect(Collectors.toList());

        Map<String, Object> params = new HashMap<>();
        params.put("entityName", entityName);
        params.put("columns", columnList);
        params.put("endpoint", endpoint);

        String generatedHtml = templateEngine.generateFromTemplate("frontend/datatables_view.ftl", params);
        
        model.addAttribute("title", "Vista DataTables: " + entityName);
        model.addAttribute("generatedCode", generatedHtml);
        model.addAttribute("language", "html");
        
        return "frontend/result";
    }
}
