package com.kakao.cafe.controller;

import com.kakao.cafe.dto.PostCreateRequest;
import com.kakao.cafe.service.PostService;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String getPostList(Model model) {
        model.addAttribute("postList", postService.getAllPostDetail());
        return "posts/list";
    }

    @PostMapping
    public String write(@Valid PostCreateRequest requestDto) {
        postService.write(requestDto);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("post", postService.getPostDetailById(id));
        return "posts/detail";
    }
}
