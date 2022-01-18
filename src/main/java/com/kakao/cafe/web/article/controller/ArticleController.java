package com.kakao.cafe.web.article.controller;

import com.kakao.cafe.web.article.domain.Article;
import com.kakao.cafe.web.reply.domain.Reply;
import com.kakao.cafe.web.reply.service.ReplyService;
import com.kakao.cafe.web.user.domain.User;
import com.kakao.cafe.web.article.dto.ArticleCreateDTO;
import com.kakao.cafe.web.article.dto.ArticleUpdateDTO;
import com.kakao.cafe.web.article.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Controller
public class ArticleController {
    private final ArticleService articleService;
    private final ReplyService replyService;
    private final Logger logger;

    ArticleController(ArticleService articleService, ReplyService replyService) {
        this.articleService = articleService;
        this.replyService = replyService;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("articleList", articleService.getArticleListNotDeleted());
        return "index";
    }

    @GetMapping("/articles/form")
    public String getQnaForm(HttpSession session) {
        Object sessionedUser = session.getAttribute("sessionedUser");
        if (sessionedUser == null) {
            return "redirect:/users/login";
        }
        return "qna/form";
    }

    @PostMapping("/articles/form")
    public String createArticle(HttpSession session, String title, String contents) {
        Object sessionedUser = session.getAttribute("sessionedUser");
        if (sessionedUser == null) {
            return "redirect:/users/login";
        }
        ArticleCreateDTO articleCreateDTO = new ArticleCreateDTO(((User)sessionedUser).getUserId(), title, contents);
        articleService.writeArticle(articleCreateDTO);
        return "redirect:/";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(Model model, HttpSession session, @PathVariable long id) {
        Article article = articleService.getArticleById(id);
        List<Reply> replyList = replyService.getReplyListByArticleId(id);
        model.addAttribute("article", article);
        model.addAttribute("replyList", replyList);
        Object sessionedUser = session.getAttribute("sessionedUser");
        if (sessionedUser == null) {
            return "redirect:/users/login";
        }
        if (((User)sessionedUser).getUserId().equals(article.getWriter())) {
            model.addAttribute("modifiable", true);
        }
        return "qna/show";
    }

    @GetMapping("/articles/{id}/form")
    public String getUpdateArticleForm(Model model, HttpSession session, @PathVariable long id) {
        Article article = articleService.getArticleById(id);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("contents", article.getContents());
        return "qna/update-form";
    }

    @PutMapping("/articles/{id}/form")
    public String updateArticle(Model model, HttpSession session, @PathVariable long id, String title, String contents) {
        Article article = articleService.getArticleById(id);
        ArticleUpdateDTO articleUpdateDTO = new ArticleUpdateDTO(article.getId(), article.getWriter(), title, contents, article.getModifiedTime());
        articleService.updateArticle(articleUpdateDTO);
        return "redirect:/";
    }

    @DeleteMapping("/articles/{id}")
    public String deleteArticle(HttpSession session, @PathVariable long id) {
        Article article = articleService.getArticleById(id);
        Object sessionedUser = session.getAttribute("sessionedUser");
        if (sessionedUser == null) {
            return "redirect:/users/login";
        }
        if (!((User)sessionedUser).getUserId().equals(article.getWriter())) {
            return "redirect:/users/login";
        }
        articleService.deleteArticleById(id);
        return "redirect:/";
    }

    private Optional<User> getSessionedUser(HttpSession session) {
        Object value = session.getAttribute("sessionedUser");
        return Optional.ofNullable((User)value);
    }
}
