package com.kakao.cafe.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @GetMapping("/join-form")
    public String userJoinForm() {
        logger.info("GET /join-form : Load join form");
        return "user/form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        logger.info("GET /login-form : Load login form");
        return "user/login";
    }

    @GetMapping("/post-form")
    public String postForm() {
        logger.info("GET /post-form : Load article post form");
        return "post/form";
    }
}
