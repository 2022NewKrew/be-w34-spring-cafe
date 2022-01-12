package com.kakao.cafe.controller;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.dto.post.CreatePostDto;
import com.kakao.cafe.dto.post.ShowPostDto;
import com.kakao.cafe.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@RequiredArgsConstructor
@Controller
@Slf4j
public class PostController {
    private final PostService postService;

    @GetMapping("/")
    public String postHome(Model model){
        List<ShowPostDto> posts = postService.findAllPost();
        model.addAttribute("posts", posts);
        model.addAttribute("postSize", posts.size());

        return "index";
    }

    @PostMapping("/post")
    public String addPost(@ModelAttribute CreatePostDto postDto){
        Post post = postService.createPost(postDto);
        log.info("CreatPost - {}", post);
        return "redirect:/";
    }

    @GetMapping("/post/{postId}")
    public String detailPost(@PathVariable Long postId, Model model){
        model.addAttribute("post", postService.findPost(postId));
        return "post/show";
    }
}
