package com.devhelper.controller;

import com.devhelper.repository.AiPromptRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AiPromptController {

    private final AiPromptRepository repository;

    public AiPromptController(AiPromptRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/ai-prompts")
    public String index(Model model) {
        // Obtenemos de DB (cargados ppor V2__Seed_Data)
        model.addAttribute("prompts", repository.findAll());
        return "ai/index";
    }
}
