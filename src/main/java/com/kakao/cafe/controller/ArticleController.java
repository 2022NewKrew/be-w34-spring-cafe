package com.kakao.cafe.controller;

import com.kakao.cafe.config.auth.LoginUser;
import com.kakao.cafe.dto.RequestArticleDto;
import com.kakao.cafe.dto.SessionUser;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/posts/form")
    public String showCreateArticlePage(@LoginUser SessionUser user) {
        log.info("GET /posts/form");
        return "post/form";
    }

    @PostMapping("/posts/form")
    public String addArticle(@ModelAttribute RequestArticleDto articleDto, @LoginUser SessionUser user) {
        log.info("POST /posts/form {}", articleDto);

        articleService.createArticle(user.getId(), articleDto);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getAllArticles(Model model) {
        model.addAttribute("posts", articleService.getAllArticles());
        return "index";
    }

    @GetMapping("/articles/{id}")
    public String getArticleDetail(@PathVariable int id, Model model, @LoginUser SessionUser user) {
        long authorId = articleService.getAuthorIdOfArticle(id);
        if (user.getId() == authorId){
            model.addAttribute("isMyArticle", true);
        }
        model.addAttribute("post", articleService.getArticleById(id));
        return "post/show";
    }

    @GetMapping("/articles/{id}/form")
    public String showEditArticlePage(@PathVariable long id, Model model, @LoginUser SessionUser user) {
        long authorId = articleService.getAuthorIdOfArticle(id);
        if (user.getId() != authorId) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }
        model.addAttribute("article", articleService.getArticleById(id));
        return "post/updateForm";
    }

    @PostMapping("/articles/{id}/form")
    public String editArticle(@PathVariable long id, @ModelAttribute RequestArticleDto articleDto, Model model, @LoginUser SessionUser user){
        long authorId = articleService.getAuthorIdOfArticle(id);
        if (user.getId() != authorId) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }
        articleService.updateArticle(id, articleDto);
        return "redirect:/articles/{id}";
    }


}
