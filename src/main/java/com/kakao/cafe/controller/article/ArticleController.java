package com.kakao.cafe.controller.article;

import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.dto.article.ArticleAddRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/articles")
    public String add(@ModelAttribute() ArticleAddRequestDto artDto) {
        articleService.add(artDto);
        return "redirect:/";
    }

    @GetMapping("/articles/{index}")
    public String findByIndex(@PathVariable int index, Model model) {
        model.addAttribute("article", articleService.findByIndex(index));
        return "qna/show";
    }
}
