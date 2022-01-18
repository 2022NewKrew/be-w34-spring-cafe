package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.dto.ArticleResponse;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.core.SessionConst;
import com.kakao.cafe.domain.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ArticleService postService;

    @GetMapping("/")
    public String mainPage(Model model, @SessionAttribute(name = SessionConst.LOGIN_COOKIE, required = false) User user) {
        List<ArticleResponse> questionPostList = postService.findAll();
        model.addAttribute("questions", questionPostList);
        if(user != null) {
            model.addAttribute("user", user);
        }

        return "index";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }
}
