package org.nazriaz.sb.controller;

import org.nazriaz.sb.service.ApplicationUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class RegistrationController {
    private final
    ApplicationUserDetailsService applicationUserDetailsService;

    public RegistrationController(ApplicationUserDetailsService applicationUserDetailsService) {
        this.applicationUserDetailsService = applicationUserDetailsService;
    }

    @GetMapping("/registration")
    public String get() {
        return "registration";
    }

    @PostMapping("/registration")
    public String post(@RequestParam String username, @RequestParam String password) {
        boolean registrationSuccess = applicationUserDetailsService.registerNewApplicationUser(username, password);
        return registrationSuccess ? "redirect:/login" : "redirect:/registration";
    }
}