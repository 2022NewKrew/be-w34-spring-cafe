package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.ArticleResponse;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ArticleService postService;

    @GetMapping("/")
    public String mainPage(Model model) {
        List<ArticleResponse> questionPostList = postService.findAll();
        model.addAttribute("questions", questionPostList);

        return "index";
    }
}
