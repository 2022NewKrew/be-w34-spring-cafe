package com.kakao.cafe.controller;

import com.kakao.cafe.dto.QuestionCreateRequest;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final ArticleService articleService = new ArticleService();

    @PostMapping("/questions")
    public String createQuestion(@ModelAttribute QuestionCreateRequest question){
        logger.info("POST:/questions 게시물등록 {}", question.getTitle());
        articleService.saveQuestion(question);
        return "redirect:/";
    }

}
