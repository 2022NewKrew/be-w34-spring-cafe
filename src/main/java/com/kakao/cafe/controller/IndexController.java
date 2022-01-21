package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.SessionUser;
import com.kakao.cafe.domain.article.ArticleService;
import com.kakao.cafe.domain.article.dto.ArticleSimpleResponseDto;
import com.kakao.cafe.domain.user.UserService;
import com.kakao.cafe.domain.user.dto.UserLoginRequestDto;
import com.kakao.cafe.domain.user.dto.UserResponseDto;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Paging;
import com.kakao.cafe.util.PagingRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    private final UserService userService;
    private final ArticleService articleService;

    public IndexController(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String index(Model model, @Page PagingRequest pagingRequest) {
        Paging<ArticleSimpleResponseDto> pagingOfArticles = articleService.retrievePagingOfArticles(pagingRequest);
        model.addAttribute("articles", pagingOfArticles);
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
