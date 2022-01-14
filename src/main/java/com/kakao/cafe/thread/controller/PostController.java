package com.kakao.cafe.thread.controller;

import com.kakao.cafe.thread.service.PostService;
import com.kakao.cafe.thread.dto.PostCreationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public String processCreationForm(@Validated @RequestParam PostCreationForm postCreationForm) {
        Long postId = postService.addFromForm(postCreationForm);
        return "redirect:posts/" + postId;
    }

    @GetMapping(value = {"/", "/posts"})
    public String listPosts(Model model) {
        model.addAttribute("posts", postService.getAll());
        return "index";
    }

    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.get(id));
        return "post/show";
    }
}
