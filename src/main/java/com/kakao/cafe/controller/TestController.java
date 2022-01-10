package com.kakao.cafe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class TestController {
    @GetMapping("/test")
    public String getTest(@RequestParam String name, @RequestParam String word) {
        return "name: " + name + ", word: " + word;
    }
}
