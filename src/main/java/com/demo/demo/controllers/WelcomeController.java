package com.demo.demo.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class WelcomeController {
    
    @GetMapping
    public String welcome(){
        return "Welcome to SpringBoot Programming!";
    }

}
