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

import javax.naming.NoPermissionException;
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

    @RequestMapping(value = "/articles/form", method = RequestMethod.GET)
    public String writeForm(@RequestAttribute("sessionedUser") User user, Model model) {
        model.addAttribute("writer", user.getName());
        return "qna/form";
    }

    @RequestMapping(value = "/articles/{articleId}/form", method = RequestMethod.GET)
    public String updateForm(@PathVariable long articleId,
                             @RequestAttribute("sessionedUser") User user,
                             Model model) {
        try {
            ArticleContents articleContents = checkPermission(user, articleId);
            model.addAttribute("articleContents", articleContents);
            return "qna/updateForm";
        } catch (NoPermissionException e) {
            return "redirect:/nopermission";
        }
    }

    @PostMapping("/questions")
    public String addArticle(String title,
                             String contents,
                             @SessionAttribute("sessionedUser") User user) {
        articleService.createArticle(new ArticleCreateCommand(
                user.getName(),
                user.getUserId(),
                title,
                contents));
        return "redirect:/";
    }

    @RequestMapping(value = "/questions/{articleId}/update", method = RequestMethod.PUT)
    public String updateArticle(@PathVariable long articleId,
                                @RequestAttribute("sessionedUser") User user,
                                String title,
                                String contents) {
        try {
            checkPermission(user, articleId);
            articleService.modifyArticle(articleId, new ArticleModifyCommand(title, contents));
            return "redirect:/articles/{articleId}";
        } catch (NoPermissionException e) {
            return "redirect:/nopermission";
        }
    }

    @RequestMapping(value = "questions/{articleId}", method = RequestMethod.DELETE)
    public String deleteArticle(@PathVariable long articleId,
                                @RequestAttribute("sessionedUser") User user) {
        try {
            checkPermission(user, articleId);
            articleService.deleteArticle(articleId);
            return "redirect:/";
        } catch (NoPermissionException e) {
            return "redirect:/nopermission";
        }
    }

    private ArticleContents checkPermission(User user, long articleId) throws NoPermissionException{
        ArticleContents articleContents = articleService.getArticle(articleId);
        if (userNotPermitted(user, articleContents)) {
            throw new NoPermissionException("User has no permission to this article");
        }
        return articleContents;
    }

    private boolean userNotPermitted(User user, ArticleContents articleContents) {
        return !user.getUserId().equals(articleContents.getWriterId());
    }
}
