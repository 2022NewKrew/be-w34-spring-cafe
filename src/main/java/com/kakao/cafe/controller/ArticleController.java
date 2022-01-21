package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.ArticleCreateRequest;
import com.kakao.cafe.interceptor.AuthenticationSecured;
import com.kakao.cafe.interceptor.PersonalAuthorizationSecured;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final ReplyService replyService;

    @PostMapping(value = "/articles", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @AuthenticationSecured
    @PersonalAuthorizationSecured
    public String createArticle(ArticleCreateRequest request) {
        log.info("start createArticle()");
        articleService.createArticle(request);
        return "redirect:/";
    }

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        model.addAttribute("articles", articleService.list());

        User sessionedUser = (User) request.getSession().getAttribute("sessionedUser");
        if (sessionedUser == null) {
            return "/index";
        }

        model.addAttribute("userPk", sessionedUser.getId());
        return "/index";
    }

    @GetMapping("/articles/{id}")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("article", articleService.detail(id));
        model.addAttribute("replies", replyService.list(id));
        return "/articles/show";
    }

    @GetMapping("/articles/form")
    @AuthenticationSecured
    public String form(HttpServletRequest request, Model model) {
        User sessionedUser = (User) request.getSession().getAttribute("sessionedUser");
        model.addAttribute("name", sessionedUser.getName());
        model.addAttribute("userPk", sessionedUser.getId());
        return "/articles/form";
    }

    @GetMapping("/articles/{id}/form")
    @AuthenticationSecured
    @PersonalAuthorizationSecured
    public String updateForm(HttpServletRequest request, @PathVariable int id, Model model) {
        User sessionedUser = (User) request.getSession().getAttribute("sessionedUser");
        model.addAttribute("article", articleService.detail(id));
        model.addAttribute("name", sessionedUser.getName());
        model.addAttribute("userPk", sessionedUser.getId());
        return "/articles/update-form";
    }

    @PutMapping("/articles/{id}")
    @AuthenticationSecured
    @PersonalAuthorizationSecured
    public String update(@PathVariable int id, ArticleCreateRequest request) {
        log.info("start update()");
        articleService.update(id, request);
        return"redirect:/";
    }

    @DeleteMapping("/articles/{id}")
    @AuthenticationSecured
    @PersonalAuthorizationSecured
    public String delete(@PathVariable int id) {
        log.info("start delete()");
        articleService.delete(id);
        return "redirect:/";
    }

}
