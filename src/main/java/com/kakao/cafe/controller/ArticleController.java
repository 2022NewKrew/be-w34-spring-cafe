package com.kakao.cafe.controller;

import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ArticleController {

    private final ArticleService service;

    @Autowired
    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @PostMapping("/articles")
    public String write(
            @RequestParam(value="writer") String author,
            @RequestParam String title,
            @RequestParam(value="contents") String content,
            HttpSession session
    ) {
        String ownerId = (String) session.getAttribute("id");
        service.create(ownerId, author, title, content);
        return "redirect:/";
    }
}
