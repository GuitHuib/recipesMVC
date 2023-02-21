package com.RyanSBA.mvc.controller;

import com.RyanSBA.mvc.DTO.UserDto;
import com.RyanSBA.mvc.model.User;
import com.RyanSBA.mvc.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
@Slf4j
public class UserController {

    @Autowired
    UserServiceImpl service;
    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/sign-up")
    public String newUser() {
        return "signup";
    }

    @PostMapping("/sign-up")
    public RedirectView newUser(@ModelAttribute UserDto dto, HttpServletRequest req) {
        service.createUser(dto);
        try {
            req.login(dto.getEmail(), dto.getPassword());
        } catch(ServletException e) {
            log.error("Login Error ", e);
        }
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String showUser(Model model, Principal principle) {
        User user = service.findByEmail(principle.getName());
        model.addAttribute("user", user);
        return "user";
    }


}
