package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.SessionUser;
import com.kakao.cafe.domain.article.ArticleService;
import com.kakao.cafe.domain.article.dto.ArticleResponseDto;
import com.kakao.cafe.domain.user.UserService;
import com.kakao.cafe.domain.user.dto.UserLoginRequestDto;
import com.kakao.cafe.domain.user.dto.UserResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {

    private final UserService userService;
    private final ArticleService articleService;

    public IndexController(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<ArticleResponseDto> articles = articleService.retrieveAllArticles();
        model.addAttribute("articles", articles);
        return "index";
    }

    @PostMapping("/login")
    public String login(UserLoginRequestDto loginRequestDto, HttpSession httpSession) {
        UserResponseDto userInfo = userService.loginUser(loginRequestDto);
        httpSession.setAttribute("sessionUser", new SessionUser(userInfo));
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("sessionUser");
        return "redirect:/";
    }
}
