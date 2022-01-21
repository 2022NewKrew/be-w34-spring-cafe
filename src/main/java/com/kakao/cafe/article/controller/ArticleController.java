package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.request.ArticleRequest;
import com.kakao.cafe.article.dto.response.ArticleDetailResponse;
import com.kakao.cafe.article.dto.response.ArticleResponse;
import com.kakao.cafe.article.dto.response.ArticleUpdateResponse;
import com.kakao.cafe.article.dto.response.ArticlesResponse;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.user.dto.response.UserResponse;
import com.kakao.cafe.user.service.UserService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/articles")
    public String writeArticle(HttpSession session, @Valid ArticleRequest articleRequest) {
        UserResponse userResponse = (UserResponse) session.getAttribute("sessionUser");
        ArticleResponse articleResponse = articleService.save(userResponse.getId(), articleRequest);
        logger.info("글쓰기 완료: {}", articleResponse);
        return "redirect:/";
    }

    @GetMapping("/")
    public String searchArticles(Model model) {
        ArticlesResponse articlesResponse = articleService.findAll();
        logger.info("글목록 조회 완료");
        model.addAttribute("articles", articlesResponse);
        return "/index";
    }

    @GetMapping("/articles/{id}")
    public String searchArticle(HttpSession session, Model model, @PathVariable Long id) {
        UserResponse userResponse = (UserResponse) session.getAttribute("sessionUser");
        ArticleDetailResponse articleDetailResponse = articleService.findById(id, userResponse.getId());
        logger.info("글조회 완료: {}", articleDetailResponse);
        model.addAttribute("article", articleDetailResponse);
        return "/post/show";
    }

    @GetMapping("/articles/update-form/{id}")
    public String updateArticleForm(HttpSession session, Model model, @PathVariable Long id) {
        UserResponse userResponse = (UserResponse) session.getAttribute("sessionUser");
        ArticleUpdateResponse articleUpdateResponse = articleService.findUpdateArticleById(id, userResponse.getId());
        logger.info("글 수정을 위한 데이터조회 완료: {}", articleUpdateResponse);
        model.addAttribute("article", articleUpdateResponse);
        return "/post/update";
    }

    @PutMapping("/articles/{id}")
    public String updateArticle(HttpSession session, @PathVariable Long id,
        @Valid ArticleRequest articleRequest) {
        UserResponse userResponse = (UserResponse) session.getAttribute("sessionUser");
        articleService.update(id, userResponse.getId(), articleRequest);
        logger.info("글수정 완료");
        return "redirect:/articles/" + id;
    }

    @DeleteMapping("/articles/{id}")
    public String deleteArticle(HttpSession session, @PathVariable Long id){
        UserResponse userResponse = (UserResponse) session.getAttribute("sessionUser");
        articleService.delete(id, userResponse.getId());
        logger.info("글삭제 완료");
        return "redirect:/";
    }
}
