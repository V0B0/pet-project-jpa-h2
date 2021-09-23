package com.v0b0.pstlabstesttask.controllers;

import com.v0b0.pstlabstesttask.service.GlobalServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@Controller
public class WelcomeController {

    private final GlobalServiceImpl service;

    public WelcomeController(GlobalServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/")
    public String welcome(Model model){
        System.out.println("welcome()");
        model.addAttribute("company", service.getCompany().stream()
                .filter(company -> !company.getWorkers().isEmpty()).collect(Collectors.toList()));
        model.addAttribute("people", service.getPeople());
        return "welcome";
    }
}
