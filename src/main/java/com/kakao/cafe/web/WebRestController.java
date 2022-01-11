package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebRestController {

    Logger logger = LoggerFactory.getLogger(WebRestController.class);

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
