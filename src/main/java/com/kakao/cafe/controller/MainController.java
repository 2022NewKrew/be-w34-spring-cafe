package com.kakao.cafe.controller;

import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final ArticleService articleService ;

    @GetMapping("/")
    public String home(Model model){
        logger.info("GET:/");
        model.addAttribute("questions", articleService.findAllQuestions());
        return "index";
    }

}
