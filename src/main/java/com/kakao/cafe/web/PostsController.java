package com.kakao.cafe.web;

import com.kakao.cafe.service.PostsService;
import com.kakao.cafe.web.dto.PostResponseDto;
import com.kakao.cafe.web.dto.PostsCreateRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class PostsController {

    private final PostsService postsService = new PostsService();

    @GetMapping("/")
    public String mainPage(Model model) {
        ArrayList<PostResponseDto> posts = postsService.findAll();
        model.addAttribute("posts", posts);
        return "index";
    }

    @PostMapping("/api/posts")
    public String posts(PostsCreateRequestDto requestDto) {
        requestDto.setWriter("tester");
        postsService.save(requestDto);

        return "redirect:/";
    }

    @GetMapping("/posts/{index}")
    public String postsDetail(@PathVariable("index") Long index, Model model) {
        PostResponseDto post = postsService.findById(index);
        model.addAttribute("post", post);
        return "post/show";
    }

    @GetMapping("/post-article")
    public String post_form() {
        return "post/form";
    }

}
