package com.kakao.cafe.controller;

import com.kakao.cafe.domain.WritePostRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    public PostController() {
    }

    @PostMapping("/posts")
    public String write(WritePostRequest postDto) {
        // TODO - 게시글 작성
        return "redirect:/";
    }

    @GetMapping("/")
    public String postList(Model model) {
        // TODO - 게시글 전체 리스트
        return "index";
    }

    @GetMapping("/posts/{postId}")
    public String postById(@PathVariable String postId, Model model) {
        // TODO - 게시글 보기
        return "posts/show";
    }

}
