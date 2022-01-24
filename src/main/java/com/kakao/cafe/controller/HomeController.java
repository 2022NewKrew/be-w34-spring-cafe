package com.kakao.cafe.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/")
    public String index(){
        logger.info("index");
        return "index";
    }


}
