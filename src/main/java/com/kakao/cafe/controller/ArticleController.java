package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.ArticleCreateRequest;
import com.kakao.cafe.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "/articles", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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
        return "/articles/show";
    }

    @GetMapping("/articles/form")
    public String form(HttpServletRequest request, Model model) {
        User sessionedUser = (User) request.getSession().getAttribute("sessionedUser");
        model.addAttribute("name", sessionedUser.getName());
        model.addAttribute("userPk", sessionedUser.getId());
        return "/articles/form";
    }

    @GetMapping("/articles/{id}/form")
    public String updateForm(HttpServletRequest request, @PathVariable Integer id, Model model) {
        User sessionedUser = (User) request.getSession().getAttribute("sessionedUser");
        model.addAttribute("article", articleService.detail(id));
        model.addAttribute("name", sessionedUser.getName());
        model.addAttribute("userPk", sessionedUser.getId());
        return "/articles/update-form";
    }

    @PutMapping("/articles/{id}")
    public String update(@PathVariable Integer id, ArticleCreateRequest request) {
        log.info("start update()");
        articleService.update(id, request);
        return"redirect:/";
    }

    @DeleteMapping("/articles/{id}")
    public String delete(@PathVariable Integer id) {
        log.info("start delete()");
        articleService.delete(id);
        return"redirect:/";
    }

}
