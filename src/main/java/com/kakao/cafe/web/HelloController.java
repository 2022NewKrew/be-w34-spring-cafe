package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloController {

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/fruit")
    public String hello(String name, int sugar, Model model) {
        Fruit f = new Fruit(name, sugar);
        logger.info("과일: {}, 당도: {}", f.getName(), f.getSugar());
        model.addAttribute("fruit", f);
        return "hello";
    }

    @PostMapping("/fruit")
    public String createFruit(Fruit f) {
        logger.info("Create: ", f);
        return String.format("redirect:/fruit?name=%s&sugar=%s", f.getName(), f.getSugar());
    }
}
