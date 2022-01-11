package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.ArticleRegisterRequestDto;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public void register(@ModelAttribute ArticleRegisterRequestDto articleRegisterRequestDto) {

    }
}
