package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.ArticleResponseDto;
import com.kakao.cafe.controller.dto.QuestionDto;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService postService;

    @GetMapping("qna/form")
    public String getForm() {
        return "qna/form";
    }

    @PostMapping("/questions")
    public String createQuestion(@ModelAttribute QuestionDto questionDto) {
        postService.createPost(questionDto);
        return "redirect:/";
    }

    @GetMapping("/articles/{id}")
    public String getArticleInfo(@PathVariable Long id, Model model) {
        ArticleResponseDto article = postService.findById(id);
        model.addAttribute("article", article);
        return "qna/show";
    }
}
