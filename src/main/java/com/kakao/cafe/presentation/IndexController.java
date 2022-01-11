package com.kakao.cafe.presentation;

import com.kakao.cafe.application.dto.result.QuestionPostDetailListResult;
import com.kakao.cafe.application.post.QuestionPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {

    private final QuestionPostService questionPostService;

    @GetMapping("")
    public String allPost(Model model) {
        QuestionPostDetailListResult allPost = questionPostService.getAllPost();
        model.addAttribute("count", allPost.getDetailResults().size());
        model.addAttribute("posts", allPost.getDetailResults());
        return "index";
    }
}
