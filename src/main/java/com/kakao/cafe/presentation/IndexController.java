package com.kakao.cafe.presentation;

import com.kakao.cafe.application.dto.result.QuestionPostDetailListResult;
import com.kakao.cafe.application.post.QuestionPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {

    private final QuestionPostService questionPostService;

    @GetMapping("")
    public String allPost(Model model, HttpServletRequest request) {
        QuestionPostDetailListResult allPost = questionPostService.getAllPost();
        model.addAttribute("count", allPost.getDetailResults().size());
        model.addAttribute("posts", allPost.getDetailResults());

        if(request.getSession().getAttribute("user-id") != null) {
            return "after/afterindex";
        }
        return "index";
    }
}
