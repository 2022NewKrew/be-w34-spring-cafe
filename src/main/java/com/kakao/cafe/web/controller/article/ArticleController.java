package com.kakao.cafe.web.controller.article;

import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.web.dto.article.ArticleAddRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/questions")
    public String add(@ModelAttribute() ArticleAddRequestDto artDto) {
        articleService.add(artDto);
        return "redirect:/";
    }
}
