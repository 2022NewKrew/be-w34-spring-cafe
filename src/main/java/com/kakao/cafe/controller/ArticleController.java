package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.ArticleFormRequest;
import com.kakao.cafe.dto.PreviewArticleResponse;
import com.kakao.cafe.dto.ReplyInfoResponse;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final ReplyService replyService;

    public ArticleController(ArticleService articleService, ReplyService replyService) {
        this.articleService = articleService;
        this.replyService = replyService;
    }

    @GetMapping("/")
    public String findArticleList(Model model) {
        List<PreviewArticleResponse> articles = articleService.findArticleList();
        model.addAttribute("articles", articles);
        return "qna/list";
    }

    @PostMapping("/questions")
    public String create(ArticleFormRequest articleFormRequest, HttpSession session) {
        Object value = session.getAttribute("sessionedUser");

        articleService.save(articleFormRequest, ((User) value).getName());
        return "redirect:/";
    }

    @PutMapping("/questions/{id}")
    public String update(@PathVariable("id") Long id, ArticleFormRequest articleFormRequest, HttpSession session) {
        Object value = session.getAttribute("sessionedUser");
        User sessionUser = (User) value;
        try {
            articleService.updateArticleInfo(id, sessionUser, articleFormRequest);
        } catch (AuthenticationException e) {
            return "redirect:/articles/" + id;
        }
        return "redirect:/";
    }

    @DeleteMapping("/questions/{id}")
    public String delete(@PathVariable("id") Long id, HttpSession session) {
        Object value = session.getAttribute("sessionedUser");
        User sessionUser = (User) value;
        try {
            articleService.deleteArticle(id, sessionUser);
        } catch (AuthenticationException e) {
            return "redirect:/articles/" + id;
        }
        return "redirect:/";
    }
    @GetMapping("/questions/{id}/form")
    public String updateForm(@PathVariable("id") Long articleId, Model model, HttpSession session) {
        Object value = session.getAttribute("sessionedUser");
        Article article = articleService.findArticle(articleId);
        model.addAttribute("article", article);
        return "qna/updateform";
    }

    @GetMapping("/articles/{id}")
    public String findArticle(@PathVariable("id") Long articleId, Model model, HttpSession session) {
        Article article = articleService.findArticle(articleId);
        List<ReplyInfoResponse> replys = replyService.getReplyList(articleId);

        Object value = session.getAttribute("sessionedUser");
        if(value != null)
            replyService.applyVisibleEdit(replys,(User)value);
        model.addAttribute("replys",replys);
        model.addAttribute("article", article);
        return "qna/show";
    }


}
