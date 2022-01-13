package com.kakao.cafe.controller;

import com.kakao.cafe.dto.QuestionCreateRequest;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("questions")
@RequiredArgsConstructor
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final ArticleService articleService ;

    @PostMapping("")
    public String createQuestion(@ModelAttribute QuestionCreateRequest question){
        logger.info("POST:/questions 게시물등록 {}", question.getTitle());
        articleService.saveQuestion(question, LocalDateTime.now());
        return "redirect:/";
    }
    @GetMapping("{id}")
    public String viewArticle(@PathVariable Long id, Model model){
        logger.info("GET:/questions 게시물 상세보기 {}", id);
        model.addAttribute("article", articleService.findOneQuestion(id));
        return "qna/show";
    }


}
