package com.kakao.cafe.qna.controller;

import com.kakao.cafe.qna.DTO.QuestionDTO;
import com.kakao.cafe.qna.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class QnaController {
    private final QuestionService questionService;

    @PostMapping("/questions")
    public String submitArticle(@ModelAttribute QuestionDTO newQuestion) {
        log.debug("Article submitted. Title : {}, Writer : {}, Content : {}", newQuestion.getTitle(),
                newQuestion.getWriter(), newQuestion.getContents());
        questionService.submitArticle(newQuestion);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getArticleList(Model model) {
        log.debug("Article List Request");
        model.addAttribute("articles", questionService.getArticleSummaryLst());
        return "index";
    }

    @GetMapping("/articles/{index}")
    public String getArticle(Model model, @PathVariable int index) {
        log.debug("Article List Request. Index : {}", index);
        model.addAttribute("pack", questionService.getPackedArticle(index));
        return "qna/show";
    }
}
