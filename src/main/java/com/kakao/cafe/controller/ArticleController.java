package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.ArticleResponse;
import com.kakao.cafe.controller.dto.ArticleSaveForm;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleService postService;

    @PostMapping("")
    public String createQuestion(@Valid @ModelAttribute ArticleSaveForm articleSaveForm) {
        articleRepository.save(articleSaveForm);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getArticleInfo(@PathVariable Long id, Model model) {
        ArticleResponse article = postService.findById(id);
        model.addAttribute("article", article);
        return "qna/detail";
    }
}
