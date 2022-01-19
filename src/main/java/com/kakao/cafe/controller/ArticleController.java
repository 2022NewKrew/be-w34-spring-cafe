package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.exception.NotSessionInfo;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArticleController {
    private final ArticleService articleService;
    private final ReplyService replyService;

    @Autowired
    public ArticleController(ArticleService articleService, ReplyService replyService) {
        this.articleService = articleService;
        this.replyService = replyService;
    }

    @GetMapping("/")
    public String getAllQuestions(Model model) {
        List<Article> articleList = articleService.getAllQuestions();
        model.addAttribute("articleList", articleList);
        return "qna/list";
    }

    @GetMapping("/articles/{id}")
    public String getQuestionsInfo(@PathVariable String id, Model model, HttpSession httpSession) {
        Article article = articleService.findById(id, httpSession);
        List<Reply> replyList = replyService.findById(id);
        model.addAttribute("article", article);
        model.addAttribute("replyList", replyList);
        return "qna/show";
    }

    @PostMapping("/articles")
    public String addQuestions(@ModelAttribute Article article) {
        articleService.save(article);
        return "redirect:/";
    }

    @GetMapping("/articles/write")
    public String write(HttpSession httpSession) {
        if (httpSession.getAttribute("curUser") == null) {
            throw new NotSessionInfo("글을 작성하려면 로그인을 하십시오");
        }
        return "qna/form";
    }

    @DeleteMapping("/articles/deleteByWriter")
    public String deleteByWriter(@RequestParam(value = "writer") String writer) {
        articleService.deleteByWriter(writer);
        return "redirect:/";
    }

    @GetMapping("/articles/update/{id}")
    public String updateForm(@PathVariable String id, Model model, HttpSession httpSession) {
        Article article = articleService.findById(id, httpSession);
        articleService.isWriter(article, httpSession);
        model.addAttribute("article", article);
        return "qna/updateForm";
    }

    @PutMapping("/articles")
    public String updateArticle(@ModelAttribute Article article) {
        articleService.update(article);
        return "redirect:/";
    }

    @DeleteMapping("articles/delete/{id}")
    public String deleteById(@PathVariable String id, HttpSession httpSession) {
        Article article = articleService.findById(id, httpSession);
        articleService.isWriter(article, httpSession);
        articleService.deleteById(id);
        return "redirect:/";
    }
}
