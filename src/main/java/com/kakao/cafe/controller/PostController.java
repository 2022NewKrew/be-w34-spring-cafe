package com.kakao.cafe.controller;


import com.kakao.cafe.dto.post.PostReqDto;
import com.kakao.cafe.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public String createPost(@ModelAttribute PostReqDto postReqDto){
        postService.addPost(postReqDto);
        return "redirect:/";
    }
}
