package com.firestore.adapters.inbound.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("activePage", "home");
        return "home";
    }

    @GetMapping("/system")
    public String system(Model model) {
        model.addAttribute("activePage", "system");
        return "system";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("activePage", "about");
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("activePage", "contact");
        return "contact";
    }
}


