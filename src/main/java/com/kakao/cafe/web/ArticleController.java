package com.kakao.cafe.web;

import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.dto.article.ArticleContents;
import com.kakao.cafe.dto.article.ArticleCreateCommand;
import com.kakao.cafe.dto.article.ArticleModifyCommand;
import com.kakao.cafe.dto.reply.ReplyContents;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String listArticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "index";
    }

    @GetMapping("/articles/{articleId}")
    public String getArticle(@PathVariable Long articleId, Model model) {
        ArticleContents articleContents = articleService.getArticle(articleId);
        model.addAttribute("article", articleContents);
        List<ReplyContents> replyContentsList = replyService.getAllReplies(articleId);
        model.addAttribute("replies", replyContentsList);
        return "qna/show";
    }

    @GetMapping("/articles/form")
    public String writeForm(@SessionAttribute("sessionedUser") User user, Model model) {
        model.addAttribute("writer", user.getName());
        return "qna/form";
    }

    @GetMapping("/articles/{articleId}/form")
    public String updateForm(@PathVariable long articleId,
                             @SessionAttribute("sessionedUser") User user,
                             Model model) {
        ArticleContents articleContents = articleService.getArticle(articleId);
        model.addAttribute("articleContents", articleContents);
        return "qna/updateForm";
    }

    @PostMapping("/questions")
    public String addArticle(String title,
                             String contents,
                             @SessionAttribute("sessionedUser") User user) {
        articleService.createArticle(new ArticleCreateCommand(user.getName(), user.getUserId(), title, contents));
        return "redirect:/";
    }

    @PutMapping("/questions/{articleId}/update")
    public String updateArticle(@PathVariable long articleId,
                                String title,
                                String contents) {
        articleService.modifyArticle(articleId, new ArticleModifyCommand(title, contents));
        return "redirect:/articles/{articleId}";
    }

    @DeleteMapping("questions/{articleId}")
    public String deleteArticle(@PathVariable long articleId,
                                @SessionAttribute("sessionedUser") User user) {
        articleService.deleteArticle(articleId);
        return "redirect:/";
    }

    private boolean userNotPermitted(User user, ArticleContents articleContents) {
        return !user.getUserId().equals(articleContents.getWriterId());
    }
}
