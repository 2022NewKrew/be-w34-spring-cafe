package com.kakao.cafe.controller;

import com.kakao.cafe.dto.PageRequestDto;
import com.kakao.cafe.dto.PostDto;
import com.kakao.cafe.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/posts")
@Slf4j
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/new")
    public String writePostForm() {
        return "board/write_post";
    }

    @GetMapping("/{postId}")
    public String readPost(@PathVariable Long postId, Model model) {
        model.addAttribute("post", postService.getPost(postId));
        return "board/post";
    }

    @PostMapping
    public String writePost(PostDto postDto, HttpSession session) {
        return "redirect:/posts/" + postService.register(postDto);
    }

    @GetMapping
    public String postList(PageRequestDto pageRequestDto, Model model) {
        model.addAttribute("posts", postService.getList(pageRequestDto));
        return "board/list";
    }
}
