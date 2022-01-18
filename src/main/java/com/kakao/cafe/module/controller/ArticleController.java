package com.kakao.cafe.module.controller;

import com.kakao.cafe.module.service.ArticleService;
import com.kakao.cafe.module.service.InfraService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;
import static com.kakao.cafe.module.model.dto.UserDtos.*;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;
    private final InfraService infraService;

    @PostMapping
    public String postArticle(ArticlePostDto articlePostDto, HttpSession session) throws HttpSessionRequiredException {
        UserDto userDto = infraService.retrieveUserSession(session);
        articleService.postArticle(articlePostDto, userDto);
        logger.info("Post Article : {}", articlePostDto.getTitle());
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String showArticle(@PathVariable Long id, Model model, HttpSession session) throws HttpSessionRequiredException {
        infraService.retrieveUserSession(session);
        ArticleReadDto article = articleService.showArticle(id);
        model.addAttribute("article", article);
        logger.info("Get Article : {}", id);
        return "article/show";
    }

    @GetMapping("/{id}/form")
    public String getArticleUpdateForm(@PathVariable Long id, Model model, HttpSession session) throws HttpSessionRequiredException {
        ArticleReadDto article = articleService.showArticle(id);
        infraService.validateSession(session, article.getAuthorId());
        model.addAttribute("article", article);
        logger.info("Get Article Update Form : {}", id);
        return "article/updateForm";
    }

    @PutMapping("/{id}")
    public String updateArticle(@PathVariable Long id, ArticleUpdateDto articleUpdateDto, HttpSession session) throws HttpSessionRequiredException {
        infraService.validateSession(session, articleService.showArticle(id).getAuthorId());
        articleService.updateArticle(id, articleUpdateDto);
        logger.info("Update Article : {}", id);
        return "redirect:/articles/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable Long id, HttpSession session) throws HttpSessionRequiredException {
        infraService.validateSession(session, articleService.showArticle(id).getAuthorId());
        articleService.deleteArticle(id);
        logger.info("Delete Article : {}", id);
        return "redirect:/";
    }
}
