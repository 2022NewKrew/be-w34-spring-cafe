package com.kakao.cafe.controller;

import com.kakao.cafe.dto.post.PostListDto;
import com.kakao.cafe.dto.post.PostListItemDto;
import com.kakao.cafe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final PostService postService;

    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<PostListItemDto> postList = postService.getList();
        model.addAttribute("postListInfo", new PostListDto(postList.size(), postList));
        return "index";
    }
}
