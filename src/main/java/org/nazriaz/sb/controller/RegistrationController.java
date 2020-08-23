package org.nazriaz.sb.controller;

import org.nazriaz.sb.service.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class RegistrationController {
    @Autowired
    ApplicationUserDetailsService applicationUserDetailsService;

    @GetMapping("/registration")
    public String get() {
        return "registration";
    }

    @PostMapping("/registration")
    public String post(@RequestParam String username, @RequestParam String password) {
        if (applicationUserDetailsService.registerNewApplicationUser(username, password).equals("OK")) {
            return "redirect:login";
        }
        else {
            return "redirect:registration";
        }
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}