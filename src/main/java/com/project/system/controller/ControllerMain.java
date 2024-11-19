package com.project.system.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerMain {

    @GetMapping("/management")
    public String accessMain(){
        return "management/home";
    }
}
