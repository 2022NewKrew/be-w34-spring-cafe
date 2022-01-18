package com.kakao.cafe.controller;

import com.kakao.cafe.dto.QuestionCreateRequest;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("questions")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService ;

    @PostMapping("")
    public String createQuestion(@ModelAttribute QuestionCreateRequest question){
        articleService.saveQuestion(question, LocalDateTime.now());
        return "redirect:/";
    }
    @GetMapping("{id}")
    public String viewArticle(@PathVariable Long id, Model model){
        model.addAttribute("article", articleService.findOneQuestion(id));
        return "qna/show";
    }
    @GetMapping("")
    public String viewArticleList(Model model){
        model.addAttribute("questions", articleService.findAllQuestions());
        return "index";
    }


}
