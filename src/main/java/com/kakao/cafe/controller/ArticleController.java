package com.kakao.cafe.controller;

import com.kakao.cafe.dto.article.WriteArticleDto;
import com.kakao.cafe.exceptions.WrongAccessException;
import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@AllArgsConstructor
public class ArticleController {

    private ArticleService articleService;
    private UserService userService;

    // 게시글 목록 조회
    @GetMapping("/articles")
    public String showArticles(Model model) {
        model.addAttribute("articles", this.articleService.findAllArticles());
        return "article/list";
    }

    // 게시글 작성 양식
    @GetMapping("/article/post")
    public String articleForm() {
        return "article/form";
    }

    // 게시글 작성 - 로그인 필요
    @PostMapping("/article/post")
    public String postArticle(WriteArticleDto writeArticleDto, HttpSession session) throws WrongAccessException {
        this.userService.isUserLoggedin(session);
        this.articleService.save(writeArticleDto);
        return "redirect:/articles";
    }

    // 게시글 상세 조회 - 로그인 필요
    @GetMapping("/articles/{id}")
    public String showArticle(@PathVariable int id, HttpSession session, Model model) throws WrongAccessException {
        this.userService.isUserLoggedin(session);
        model.addAttribute("article", this.articleService.findArticleById(id));
        return "article/show";
    }

}
