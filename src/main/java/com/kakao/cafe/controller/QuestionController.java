package com.kakao.cafe.controller;

import com.kakao.cafe.dto.QuestionSaveDto;
import com.kakao.cafe.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/")
    public String findAll(Model model) {
        model.addAttribute("questions", questionService.findAll());
        return "index";
    }

    @PostMapping("/questions")
    public String save(@ModelAttribute() QuestionSaveDto questionSaveDto) {
        questionService.save(questionSaveDto);
        return "redirect:/";
    }
}
