package com.kakao.cafe.controller;

import com.kakao.cafe.Service.PostService;
import com.kakao.cafe.model.Post.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final PostService postService;

    @GetMapping
    public String mainPage(Model model) {
        logger.info("[GET] 메인 페이지");

        List<PostResponseDto> postList = postService.getPostList();
        model.addAttribute("postList", postList);

        return "index";
    }

}
