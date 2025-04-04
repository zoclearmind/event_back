package com.zocode.event.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestApiCotroller {
    @GetMapping
    public ResponseEntity<String> tetsApi(){
        return ResponseEntity.ok().body("APi");
    }
}
