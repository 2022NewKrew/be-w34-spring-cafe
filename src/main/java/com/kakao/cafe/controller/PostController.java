package com.kakao.cafe.controller;

import com.kakao.cafe.dto.PostCreateDto;
import com.kakao.cafe.dto.PostDetailDto;
import com.kakao.cafe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/questions")
    public String createQuestion(Model model, PostCreateDto postCreateDto) {
        postService.create(postCreateDto);
        return "redirect:/";
    }

    @GetMapping("/questions/{questionId}")
    public String getQuestion(Model model, @PathVariable int questionId) {
        PostDetailDto post = postService.get(questionId);
        model.addAttribute("post", post);
        return "qna/show";
    }
}
