package com.kakao.cafe.Controller;


import com.kakao.cafe.Domain.Article;
import com.kakao.cafe.Domain.Comment;
import com.kakao.cafe.Service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/articles")
    public String post(Article article) {
        articleService.post(article);
        logger.info("{}(title) was posted", article.getTitle());

        return "redirect:/";
    }

    @GetMapping("/")
    public String home(Model model) {
        logger.info("GET / : Get all articles");

        List<Article> findArticles = articleService.findArticles();
        
        model.addAttribute("articles", findArticles);
        model.addAttribute("articlesCount", findArticles.size());
        return "index";
    }

    @GetMapping("/articles/{articleId}")
    public String getArticle(@PathVariable("articleId") Long articleId,
                             Model model){
        logger.info("GET /article/{} : View article({})", articleId, articleId);

        Optional<Article> findArticle = articleService.findOne(articleId);
        List<Comment> findComments = articleService.findComments(articleId);

        model.addAttribute("article", findArticle.get());
        model.addAttribute("comments", findComments);
        model.addAttribute("commentsCount", findComments.size());
        return "post/show";
    }

    @PostMapping("/comments/{articleId}")
    public String postComment(@PathVariable("articleId") int articleId, Comment comment){
        logger.info("POST /comment/{} : Create a comment on article({})", articleId, articleId);

        articleService.postComment(comment);
        logger.info("Comment was posted by {}", comment.getAuthor());

        return "redirect:/articles/{articleId}";
    }
}
