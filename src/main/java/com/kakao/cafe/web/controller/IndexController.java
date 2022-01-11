package com.kakao.cafe.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/")
    public String index() {
        logger.info("GET /: response index page");
        return "index";
    }
}
