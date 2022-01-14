package com.kakao.cafe.thread;

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

    @PostMapping("/posts")
    public String processCreationForm(PostCreationForm postCreationForm) {
        Long postId = postService.addFromForm(postCreationForm);
        System.out.println(postId);
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
