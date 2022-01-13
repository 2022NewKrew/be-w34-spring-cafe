package com.kakao.cafe;

import com.kakao.cafe.domain.article.ArticleListService;
import com.kakao.cafe.domain.article.dto.ArticleResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ArticleListService articleListService;

    public HomeController(ArticleListService articleListService) {
        this.articleListService = articleListService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<ArticleResponseDto> articles = articleListService.showArticleList();
        model.addAttribute("totalSize", articles.size());
        model.addAttribute("articles", articles);
        return "/index";
    }
}
