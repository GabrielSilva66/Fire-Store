package com.project.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/management")
    public String showDashboard() {
        // Retorna a página de dashboard do sistema de gerenciamento
        return "store/home";  // Exemplo de template para a página de administração
    }
}
