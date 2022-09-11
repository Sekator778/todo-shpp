package com.example.todoshpp.controller;

import com.example.todoshpp.model.User;
import com.example.todoshpp.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


/**
 *
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @GetMapping
//    @Operation(summary = "/register page")
//    public String registerForm() {
//        return "registration";
//    }

    @PostMapping
    @Operation(summary = "register page")
    public String processRegistration(@RequestBody User user) {
        log.info("processRegistration init");
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userRepository.save(user);
        log.info("processRegistration done");

        return "home";
    }

}

