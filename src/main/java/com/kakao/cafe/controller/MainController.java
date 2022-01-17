package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.ArticleResponse;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.login.SessionConfig;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ArticleService postService;

    @GetMapping("/")
    public String mainPage(Model model, @SessionAttribute(name = SessionConfig.LOGIN_COOKIE, required = false) User user) {
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
