package com.kakao.cafe.controller;

import com.kakao.cafe.model.dto.ArticleDto;
import com.kakao.cafe.model.dto.CommentDto;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.annotation.Auth;
import com.kakao.cafe.util.annotation.MyArticle;
import com.kakao.cafe.util.annotation.MyComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping()
    public String articleListView(Model model) {
        List<ArticleDto> articleList = articleService.getArticleList();
        model.addAttribute("articles", articleList);
        return "index";
    }

    @Auth
    @GetMapping("/articles/{articleId}")
    public String articleView(@PathVariable int articleId, Model model) {
        ArticleDto article = articleService.filterArticleById(articleId);
        List<CommentDto> comments = articleService.getCommentList(articleId);
        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        model.addAttribute("commentsCount", comments.size());
        return "qna/show";
    }

    @Auth
    @GetMapping("/articles")
    public String writeArticleView() {
        return "qna/form";
    }

    @Auth
    @PostMapping("/articles")
    public String writeArticle(ArticleDto article, HttpSession session) {
        UserDto loginUser = (UserDto) session.getAttribute("sessionedUser");
        articleService.writeArticle(article, loginUser);
        return "redirect:";
    }

    @MyArticle
    @GetMapping("/articles/{articleId}/update")
    public String updateArticleView(@PathVariable int articleId, Model model) {
        ArticleDto article = articleService.filterArticleById(articleId);
        model.addAttribute("article", article);
        return "qna/updateForm";
    }

    @MyArticle
    @PutMapping("/articles/{articleId}/update")
    public String updateArticle(@PathVariable int articleId, ArticleDto article) {
        articleService.updateArticle(articleId, article);
        return "redirect:";
    }

    @MyArticle
    @DeleteMapping("/articles/{articleId}/delete")
    public String deleteArticle(@PathVariable int articleId, HttpSession session) {
        UserDto loginUser = (UserDto) session.getAttribute("sessionedUser");
        articleService.deleteArticle(articleId, loginUser);
        return "redirect:/";
    }

    @Auth
    @PostMapping("/articles/{articleId}/comments")
    public String writerComment(@PathVariable int articleId, CommentDto comment, HttpSession session) {
        UserDto loginUser = (UserDto) session.getAttribute("sessionedUser");
        articleService.writerComment(articleId, comment, loginUser);
        return "redirect:";
    }

    @MyComment
    @DeleteMapping("/articles/{articleId}/comments/{commentId}")
    public String deleteComment(@PathVariable int articleId, @PathVariable int commentId) {
        articleService.deleteComment(commentId);
        return String.format("redirect:/articles/%d", articleId);
    }
}
