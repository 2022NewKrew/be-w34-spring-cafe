package com.kakao.cafe.controller;

import com.kakao.cafe.dto.article.PostArticleDto;
import com.kakao.cafe.dto.article.ReferArticleDto;
import com.kakao.cafe.exceptions.WrongAccessException;
import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@AllArgsConstructor
public class ArticleController {

    private ArticleService articleService;
    private UserService userService;

    // 게시물 목록 조회
    @GetMapping("/articles")
    public String showArticles(Model model) {
        model.addAttribute("articles", this.articleService.findAll());
        return "article/list";
    }

    // 게시물 작성 양식
    @GetMapping("/article/post")
    public String articleForm() {
        return "article/form";
    }

    // 게시물 작성 - 로그인 필요
    @PostMapping("/article/post")
    public String postArticle(PostArticleDto writeArticleDto, HttpSession session) {
        if (!this.userService.isUserLoggedin(session)) {
            return "user/login";
        }
        this.articleService.save(writeArticleDto);
        return "redirect:/articles";
    }

    // 게시물 상세 조회 - 로그인 필요
    @GetMapping("/articles/{id}")
    public String showArticle(@PathVariable int id, HttpSession session, Model model) {
        if (!this.userService.isUserLoggedin(session)) {
            return "user/login";
        }
        model.addAttribute("article", this.articleService.findById(id));
        return "article/show";
    }

    // 게시물 수정 양식
    @GetMapping("/articles/{id}/update")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("article", this.articleService.findById(id));
        return "article/updateForm";
    }

    // 게시물 수정 - 로그인 필요, 회원 검사 필요
    @PatchMapping("/articles/{id}/update")
    public String updateArticle(PostArticleDto postArticleDto, @PathVariable int id, HttpSession session) throws WrongAccessException {
        ReferArticleDto referArticleDto = this.articleService.findById(id);
        this.userService.userValidation(referArticleDto.getWriter(), session);
        this.articleService.update(postArticleDto, id);
        return "redirect:/articles";
    }

    // 게시물 삭제 - 로그인 필요, 회원 검사 필요
    @DeleteMapping("/articles/{id}/delete")
    public String deleteArticle(@PathVariable int id, HttpSession session) throws WrongAccessException {
        ReferArticleDto referArticleDto = this.articleService.findById(id);
        this.userService.userValidation(referArticleDto.getWriter(), session);
        this.articleService.delete(id);
        return "redirect:/articles";
    }
}
