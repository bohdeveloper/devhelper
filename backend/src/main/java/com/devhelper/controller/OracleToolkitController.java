package com.devhelper.controller;

import com.devhelper.entity.OracleObject;
import com.devhelper.service.OracleToolkitService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/oracle")
public class OracleToolkitController {

    private final OracleToolkitService service;

    public OracleToolkitController(@NonNull OracleToolkitService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("objects", service.findAll());
        return "oracle/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        OracleObject obj = new OracleObject();
        obj.setType("TABLE"); // Default
        model.addAttribute("oracleObject", obj);
        return "oracle/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable @NonNull Long id, Model model) {
        return service.findById(id).map(obj -> {
            model.addAttribute("oracleObject", obj);
            return "oracle/form";
        }).orElse("redirect:/oracle");
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("oracleObject") OracleObject oracleObject, RedirectAttributes redirectAttributes) {
        service.save(oracleObject);
        redirectAttributes.addFlashAttribute("successMessage", "Objeto Oracle guardado y exportado al directorio físico exitosamente");
        return "redirect:/oracle";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable @NonNull Long id, RedirectAttributes redirectAttributes) {
        service.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Objeto Oracle eliminado");
        return "redirect:/oracle";
    }
}
