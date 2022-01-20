package com.kakao.cafe.thread.controller;

import com.kakao.cafe.exception.InvalidFormatException;
import com.kakao.cafe.thread.service.PostService;
import com.kakao.cafe.thread.dto.PostCreationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public String processCreationForm(@Validated PostCreationForm postCreationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFormatException();
        }
        Long postId = postService.addFromForm(postCreationForm);
        return "redirect:posts/" + postId;
    }

    @GetMapping
    public String listPosts(Model model) {
        model.addAttribute("posts", postService.getAll());
        return "post/list";
    }

    @GetMapping("/{id:[0-9]+}")
    public String showPost(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.get(id));
        return "post/detail";
    }
}
