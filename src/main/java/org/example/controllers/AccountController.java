package org.example.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.dto.RegisterDTO;
import org.example.services.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AccountController {

    private final UserInfoService userService;

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new RegisterDTO());
        return "account/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") RegisterDTO dto,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            return "account/register";
        }
        try {
            userService.register(dto);
            return "redirect:/login?registered";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "account/register";
        }
    }
}
