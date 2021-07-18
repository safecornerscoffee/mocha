package com.safecornerscoffee.mocha.controller;

import com.safecornerscoffee.mocha.dto.RegistrationForm;
import com.safecornerscoffee.mocha.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping("/register")
    public String registrationPage(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());

        return "registration/registration";
    }

    @PostMapping("/register")
    public String signup(RegistrationForm form) {
        registrationService.register(form);

        return "redirect:/";
    }
}
