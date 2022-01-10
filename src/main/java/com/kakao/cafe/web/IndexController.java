package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class IndexController {

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String index() {
        return "index";
    }

/*    @GetMapping("/user/form")
    public String getUserForm() {
        return
    }*/

    @PostMapping("/user/form")
    public String postUser() {
        return "redirect:index";
    }
}
