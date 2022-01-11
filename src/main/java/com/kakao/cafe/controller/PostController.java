package com.kakao.cafe.controller;

import com.kakao.cafe.dto.PostCreateRequest;
import com.kakao.cafe.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String getPostList(Model model) {
        model.addAttribute("postList", postService.getPostList());
        return "index";
    }

    @PostMapping("/posts")
    public String write(PostCreateRequest requestDto) {
        postService.write(requestDto);
        return "redirect:/";
    }

    @GetMapping("/posts/{postId}")
    public String getPostById(@PathVariable("postId") Long id, Model model) {
        model.addAttribute("post",
                postService.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다.")));
        return "posts/show";
    }
}
