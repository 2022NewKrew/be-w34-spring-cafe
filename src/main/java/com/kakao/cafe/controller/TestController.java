package com.kakao.cafe.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class TestController {
    @GetMapping("/test")
    public String getTest(@RequestParam String name, @RequestParam String word) {
        return "name: " + name + ", word: " + word;
    }
    @PostMapping("/test")
    public String postTest(@RequestParam String name, @RequestParam String word) {
        return "this is post result\n" + "name: " + name + ", word: " + word;
    }
}
