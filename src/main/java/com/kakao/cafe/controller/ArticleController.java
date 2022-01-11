package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.ArticleResponseDto;
import com.kakao.cafe.controller.dto.ArticleDto;
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

    private final ArticleService postService;
    private final Validator validator;

    @PostMapping("")
    public String createQuestion(@ModelAttribute ArticleDto articleDto) {
        validator.ArticleCheck(articleDto);
        postService.createPost(articleDto);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getArticleInfo(@PathVariable Long id, Model model) {
        ArticleResponseDto article = postService.findById(id);
        model.addAttribute("article", article);
        return "qna/detail";
    }
}
