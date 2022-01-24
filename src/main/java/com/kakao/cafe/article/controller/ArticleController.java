package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.Comment;
import com.kakao.cafe.article.dto.ArticlePostRequest;
import com.kakao.cafe.article.dto.ArticleUpdateRequest;
import com.kakao.cafe.article.dto.CommentPostRequest;
import com.kakao.cafe.article.dto.MultipleArticle;
import com.kakao.cafe.article.dto.SingleArticle;
import com.kakao.cafe.article.dto.SingleComment;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.article.service.CommentService;
import com.kakao.cafe.common.auth.LoginUser;
import com.kakao.cafe.user.dto.SessionUser;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @PostMapping
    public String postArticle(@LoginUser SessionUser user, @Valid ArticlePostRequest request) {
        Article article = request.toEntity(user.getId());
        articleService.post(article);
        return "redirect:/";
    }

    @GetMapping
    public String getAllArticles(Model model) {
        List<MultipleArticle> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "article/list";
    }

    @GetMapping("/{articleId}")
    public String getArticle(@LoginUser SessionUser user, @PathVariable Long articleId,
        Model model) {
        SingleArticle singleArticle = articleService.getSingleArticle(articleId);
        List<SingleComment> comments = commentService.getAllComments(articleId);
        model.addAttribute("article", singleArticle);
        model.addAttribute("comments", comments);
        return "article/show";
    }

    @GetMapping("/{articleId}/update-form")
    public String updateForm(@LoginUser SessionUser user, @PathVariable Long articleId,
        Model model) {
        SingleArticle singleArticle = articleService.getSingleArticle(articleId);
        model.addAttribute("article", singleArticle);
        return "article/update-form";
    }

    @PutMapping("/{articleId}")
    public String updateArticle(@LoginUser SessionUser user, @PathVariable Long articleId,
        @Valid ArticleUpdateRequest request) {
        Article article = request.toEntity(articleId);
        articleService.update(user.getId(), article);
        return "redirect:/article/" + articleId;
    }

    @DeleteMapping("/{articleId}")
    public String deleteArticle(@LoginUser SessionUser user, @PathVariable Long articleId) {
        articleService.delete(user.getId(), articleId);
        return "redirect:/";
    }

    @PostMapping("/{articleId}/comment")
    public String postComment(@LoginUser SessionUser user, @PathVariable Long articleId,
        @Valid CommentPostRequest request) {
        Comment comment = request.toEntity(user.getId(), articleId);
        commentService.save(comment);
        return "redirect:/article/" + articleId;
    }

    @DeleteMapping("/{articleId}/comment/{commentId}")
    public String deleteComment(@LoginUser SessionUser user, @PathVariable Long articleId,
        @PathVariable Long commentId) {
        commentService.delete(user.getId(), articleId, commentId);
        return "redirect:/article/" + articleId;
    }
}
