package com.kakao.cafe.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qna")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(path = "/form")
    public String createArticleForm() {
        return "qna/form";
    }

    @GetMapping(path = "/show/{title}")
    public String showArticle(@PathVariable String title, Model model) {
        Article article = articleService.findByTitle(title);
        model.addAttribute("article", new ArticleDto(article));
        return "qna/show";
    }

    @PostMapping(path = "/create")
    public String createArticle(ArticleRequest articleRequest) {
        articleService.createArticle(Article.builder()
                .writer(articleRequest.getWriter())
                .title(articleRequest.getTitle())
                .content(articleRequest.getContents())
                .build());
        return "redirect:/";
    }

}
