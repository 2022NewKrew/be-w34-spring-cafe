package com.kakao.cafe.controller;

import com.kakao.cafe.dto.PostCreateRequest;
import com.kakao.cafe.service.PostService;
import com.kakao.cafe.service.UserService;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String getPostList(Model model) {
        model.addAttribute("postList", postService.getAllPostDetail());
        return "index";
    }

    @PostMapping("/posts")
    public String write(@Valid PostCreateRequest requestDto) {
        postService.write(requestDto);
        return "redirect:/";
    }

    @GetMapping("/posts/{postId}")
    public String getPostById(@PathVariable("postId") UUID id, Model model) {
        model.addAttribute("post", postService.getPostDetailById(id));
        return "posts/show";
    }
}
