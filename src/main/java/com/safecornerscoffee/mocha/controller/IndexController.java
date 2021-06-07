package com.safecornerscoffee.mocha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("title", "Mocha");
        model.addAttribute("message", "Hello, Mocha");
        return "index";
    }
}
