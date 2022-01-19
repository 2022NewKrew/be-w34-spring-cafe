package com.kakao.cafe.controller;

import com.kakao.cafe.exception.IncorrectUserException;
import com.kakao.cafe.exception.NotLoginException;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ReplyService;
import com.kakao.cafe.service.SessionService;
import com.kakao.cafe.util.ErrorUtil;
import com.kakao.cafe.vo.Article;
import com.kakao.cafe.vo.Reply;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final SessionService sessionService;
    private final ReplyService replyService;

    public ArticleController(ArticleService articleService, SessionService sessionService, ReplyService replyService) {
        this.articleService = articleService;
        this.sessionService = sessionService;
        this.replyService = replyService;
    }

    @PostMapping("/article/create")
    public String questionForm(Article article, HttpSession session) throws Exception {
        User loginUser = sessionService.getLoginUser(session);
        articleService.addArticle(article, loginUser);
        return "redirect:/";
    }

    @GetMapping("/articles/{index}")
    public String getDetails(@PathVariable int index, Model model, HttpSession session) throws NotLoginException {
        sessionService.getLoginUser(session);

        Article article = articleService.getArticle(index);
        List<Reply> replys = replyService.getReplys(index);
        model.addAttribute("article", article);
        model.addAttribute("index", index);
        model.addAttribute("replys", replys);
        return "/qna/show";
    }

    @GetMapping("/")
    public String getArticles(Model model) {
        List<Article> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        return "index";
    }

    @GetMapping("questions/form")
    public String getQuestionForm(Model model, HttpSession session) throws NotLoginException {
        User loginUser = sessionService.getLoginUser(session);

        model.addAttribute("userId", loginUser.getUserId());
        return "/qna/form";
    }

    @GetMapping("/questions/{index}/edit")
    public String updateForm(@PathVariable int index, Model model, HttpSession session) throws Exception{
        User loginUser = sessionService.getLoginUser(session);
        Article article = articleService.getArticle(index);
        if(!ErrorUtil.checkSameString(loginUser.getUserId(), article.getWriter()))
            throw new IncorrectUserException();
        model.addAttribute("article", article);
        model.addAttribute("index", index);
        return "/qna/updateForm";
    }

    @PutMapping("/article/{index}/edit")
    public String editArticle(@PathVariable int index, Article article, HttpSession session) throws Exception {
        User loginUser = sessionService.getLoginUser(session);
        articleService.updateArticle(index, article, loginUser);
        return "redirect:/";
    }

}
