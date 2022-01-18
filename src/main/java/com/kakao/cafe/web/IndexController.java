package com.kakao.cafe.web;

import com.kakao.cafe.service.article.ArticleFindService;
import com.kakao.cafe.web.article.dto.ArticleListResponse;
import com.kakao.cafe.web.article.dto.ArticleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@Controller
public class IndexController {

    private final ArticleFindService articleFindService;

    @Autowired
    public IndexController(ArticleFindService articleFindService) {
        this.articleFindService = articleFindService;
    }

    @GetMapping("/")
    public String showArticles(Model model) {
        ArticleListResponse articleListResponse = new ArticleListResponse(
                articleFindService.findByAll()
                        .stream()
                        .map(ArticleResponse::new)
                        .collect(Collectors.toList())
        );

        model.addAttribute("articles", articleListResponse);
        return "/index";
    }
}
