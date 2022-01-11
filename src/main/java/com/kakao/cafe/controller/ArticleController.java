package com.kakao.cafe.controller;

import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.service.article.ArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class ArticleController {

    private ArticleService articleService;

    // 게시글 목록 조회
    @GetMapping("/")
    public String showAllArticles(Model model) {
        model.addAttribute("articles", this.articleService.getArticleList());
        return "article/list";
    }

    // 게시글 상세 내용 조회
    @GetMapping("/articles/{articleId}")
    public String showArticle(@PathVariable int articleId, Model model) {
        model.addAttribute("article", this.articleService.getArticleById(articleId));
        return "article/show";
    }

    // 게시글 작성 양식
    @GetMapping("/article/post")
    public String articleForm() {
        return "article/form";
    }

    // 게시글 작성
    @PostMapping("/article/post")
    public String postNewArticle(ArticleDto articleDto) {
        log.info("{}", articleDto.getWriter());
        log.info("{}", articleDto.getTitle());
        log.info("{}", articleDto.getContents());
        this.articleService.postNewArticle(articleDto);
        return "redirect:/";
    }

}
