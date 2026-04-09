package com.devhelper.controller;

import com.devhelper.entity.Snippet;
import com.devhelper.service.SnippetService;
import jakarta.validation.Valid;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/snippets")
public class SnippetController {

    private final SnippetService snippetService;

    public SnippetController(@NonNull SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("snippets", snippetService.findAll());
        return "snippets/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("snippet", new Snippet());
        return "snippets/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("snippet") @NonNull Snippet snippet, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "snippets/form";
        }
        snippetService.save(snippet);
        redirectAttributes.addFlashAttribute("successMessage", "Snippet guardado exitosamente");
        return "redirect:/snippets";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable @NonNull Long id, Model model, RedirectAttributes redirectAttributes) {
        return snippetService.findById(id).map((@NonNull Snippet snippet) -> {
            model.addAttribute("snippet", snippet);
            return "snippets/form";
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("errorMessage", "Snippet no encontrado");
            return "redirect:/snippets";
        });
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable @NonNull Long id, RedirectAttributes redirectAttributes) {
        snippetService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Snippet eliminado");
        return "redirect:/snippets";
    }
}
