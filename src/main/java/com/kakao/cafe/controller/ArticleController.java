package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.ArticleResponse;
import com.kakao.cafe.controller.dto.ArticleSaveForm;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleService postService;
    private final Validator validator;

    @PostMapping("")
    public String createQuestion(@ModelAttribute ArticleSaveForm articleSaveForm) {
        validator.ArticleCheck(articleSaveForm);
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
