package com.example.progetto_wsda.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Home Page");
        model.addAttribute("pageNumber", 1);
        return "index";
    }

}
