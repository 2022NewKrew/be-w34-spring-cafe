package com.kakao.cafe.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {


    @GetMapping("/hello")
    public String hello(String name, int sugar, Model model) {
        Fruit f = new Fruit(name, sugar);
        model.addAttribute("fruit", f);
        return "hello";
    }
}
