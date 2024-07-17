package com.example.springboot_server.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Hello From Spring-boot Server");
    }
}
