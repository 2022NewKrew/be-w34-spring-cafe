package com.kakao.cafe.controller;

import com.kakao.cafe.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class MainController {
    private final QuestionService questionService;

    @GetMapping("/")
    public String home(Model model){
        return "redirect:/questions";
    }

}
