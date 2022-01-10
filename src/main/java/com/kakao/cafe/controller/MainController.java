package com.kakao.cafe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/")
    public String home(){
        logger.info("GET:/");
        return "index";
    }

}
