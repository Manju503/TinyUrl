package com.tinyurl.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tinyurl")
public class Controller {

    Map<String, String> urlMapper = new HashMap<>();

    @PostMapping
    public ResponseEntity<String> createURL(@RequestBody String url){

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 7;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; i++){
            int randomIndex = (int)(Math.random()*chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        String shortUrl = "https://short.one/" +sb.toString();
        urlMapper.put(shortUrl, url);

        return ResponseEntity.status(HttpStatus.CREATED).body(shortUrl);
    }

    @GetMapping
    public ResponseEntity<String> getURL(@RequestParam String shortUrl){
        return ResponseEntity.ok(urlMapper.get(shortUrl));
    }
}
