package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public String goQuestion(HttpSession session){
        Object user = session.getAttribute("sessionUser");
        if(user == null){
            return "redirect:/user/login";
        }
        logger.info("go to Q&A form");
        return "/qna/form";
    }

    @PostMapping("/articles")
    public String createQuestion(Article article){
        articleService.save(article);
        logger.info("article created : {} ", article.getTitle());
        return "redirect:/";
    }

    @GetMapping("/")
    public String viewAllArticles(Model model){
        List<Article> articles = articleService.findAll();
        model.addAttribute("article", articles);
        return "/index";
    }

    @GetMapping("/articles/{id}")
    public String getArticleByIndex(@PathVariable long id, Model model){
        logger.info("id 찾기 : {} ",id);
        Article article = articleService.findOneById(id);
        model.addAttribute("article",  article);
        return "/qna/show";
    }

    @GetMapping("/articles/{id}/form")
    public String openUpdateArticle(@PathVariable long id, Model model, HttpSession session){
        Article article = articleService.findOneById(id);

        User user = (User) session.getAttribute("sessionUser");
        if(!user.getName().equals(article.getWriter())){
            return "redirect:/";
        }

        model.addAttribute("article", article);
        return "/qna/updateForm";
    }

    @PutMapping("/articles/{id}")
    public String updateArticle(@PathVariable long id, Article article){
        articleService.update(article, id);

        return "redirect:/articles/"+article.getId();
    }

    @DeleteMapping("/articles/{id}")
    public String deleteArticle(@PathVariable long id, HttpSession session){
        String writer = articleService.findOneById(id).getWriter();

        User user = (User) session.getAttribute("sessionUser");
        if(!writer.equals(user.getName())){
            return "redirect:/";
        }
        articleService.delete(id);
        return "redirect:/";
    }
}
