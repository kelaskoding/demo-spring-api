package com.demo.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/b2b")
public class B2BController {
    
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String getContacts(){
        return restTemplate.exchange(
            "https://reqres.in/api/users?page=2",
            HttpMethod.GET, null, String.class).getBody();
    }
}
