package com.devhelper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String index(Model model) {
        // En Fases posteriores se pueden cargar contadores de BD reales
        model.addAttribute("totalSnippets", 3);
        model.addAttribute("totalEntities", 1);
        model.addAttribute("totalPrompts", 4);
        
        return "dashboard/index";
    }
}
