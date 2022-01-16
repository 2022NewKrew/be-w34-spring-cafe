package com.kakao.cafe.web.article;

import com.kakao.cafe.service.article.ArticleCreateService;
import com.kakao.cafe.service.article.ArticleFindService;
import com.kakao.cafe.web.article.dto.ArticleCreateRequest;
import com.kakao.cafe.web.article.dto.ArticleDetailResponse;
import com.kakao.cafe.web.article.dto.ArticleListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;

@Controller
public class ArticleController {

    private final ArticleCreateService articleCreateService;
    private final ArticleFindService articleFindService;

    @Autowired
    public ArticleController(ArticleCreateService articleCreateService, ArticleFindService articleFindService) {
        this.articleCreateService = articleCreateService;
        this.articleFindService = articleFindService;
    }

    @PostMapping("/questions")
    public String postSave(@ModelAttribute ArticleCreateRequest requestDto) {
        articleCreateService.save(requestDto.toEntity());
        return "redirect:/";
    }

    @GetMapping("/")
    public String showArticles(Model model) {
        articleFindService.findByAll();
        model.addAttribute("articles", articleFindService.findByAll().stream()
                .map(ArticleListResponse::new)
                .collect(Collectors.toList()));
        return "/index";
    }

    @GetMapping("/post/{articleId}")
    public String showArticle(@PathVariable int articleId, Model model) {
        model.addAttribute("article", new ArticleDetailResponse(articleFindService.findById(articleId)));

        return "/post/show";
    }

}
