package com.kakao.cafe.qna.controller;

import com.kakao.cafe.qna.DTO.QuestionDTO;
import com.kakao.cafe.qna.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class QnaController {
    private final QuestionService questionService;
    Logger logger = LoggerFactory.getLogger(com.kakao.cafe.user.controller.UserController.class);

    @PostMapping("/questions")
    public String submitArticle(@ModelAttribute QuestionDTO newQuestion) {
        logger.debug("Article submitted. Title : {}, Writer : {}", newQuestion.getTitle(), newQuestion.getWriter());
        questionService.submitArticle(newQuestion);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getUserList(Model model) {
        model.addAttribute("articles", questionService.getArticleSummaryLst());
        logger.info("date : {}", questionService.getArticleSummaryLst().get(0).getDate());
        return "index";
    }
}
