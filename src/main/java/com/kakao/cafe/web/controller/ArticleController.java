package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.service.ArticleService;
import com.kakao.cafe.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {
    private final ArticleService articleService;
    private final Logger logger;

    ArticleController() {
        this.articleService = new ArticleService();
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }


}
