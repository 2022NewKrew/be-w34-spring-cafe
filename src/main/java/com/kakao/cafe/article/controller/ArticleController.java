package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.ArticlePostDto;
import com.kakao.cafe.article.dto.ArticleRequest;
import com.kakao.cafe.article.dto.CommentDto;
import com.kakao.cafe.article.dto.CommentRequest;
import com.kakao.cafe.article.exception.ArticleAuthorMismatchException;
import com.kakao.cafe.article.exception.ArticleCantDeleteException;
import com.kakao.cafe.article.exception.CommentAuthorMismatchException;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.article.service.CommentService;
import com.kakao.cafe.auth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final AuthService authService;
    private final CommentService commentService;

    @GetMapping("/form")
    public String getArticleFormPage(){
        return "/qna/form";
    }

    @PostMapping("/question")
    public String writeArticle(ArticleRequest articleRequest){
        articleService.writeArticle(articleRequest);

        return "redirect:/";
    }

    @DeleteMapping("/question/{id}")
    public String deleteArticle(@PathVariable Long id){
        String loginUserId = authService.getLoginUserId();
        if (!articleService.isAuthor(id, loginUserId)) {
            throw new ArticleAuthorMismatchException();
        }

        if(articleService.canDeleteArticle(id, loginUserId)){
            articleService.deleteArticle(id);
            return "redirect:/";
        }
        throw new ArticleCantDeleteException();
    }

    @GetMapping("/show/{id}")
    public String getArticleShowPage(@PathVariable Long id, Model model){
        ArticlePostDto article = articleService.getArticlePostDtoById(id);
        List<CommentDto> comments = commentService.getCommentDto(id);

        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        model.addAttribute("numberOfComments", comments.size());
        return "/qna/show";
    }

    @GetMapping("/update/{id}")
    public String getUpdateArticlePage(@PathVariable Long id, Model model) {
        String loginUserId = authService.getLoginUserId();
        if (articleService.isAuthor(id, loginUserId)) {
            model.addAttribute("id", id);
            return "/qna/updateForm";
        }

        throw new ArticleAuthorMismatchException();
    }

    @PutMapping("/update/{id}")
    public String updateArticle(@PathVariable Long id, ArticleRequest articleRequest) {
        articleService.updateArticle(id, articleRequest);
        return "redirect:/";
    }

    @PostMapping("/comment/{articleId}")
    public String addArticleComment(@PathVariable Long articleId, CommentRequest commentRequest) {
        log.info("Comment request : {}", commentRequest.toString());
        String author = authService.getLoginUserId();

        commentService.addComment(articleId, author, commentRequest);
        return "redirect:/article/show/" + articleId;
    }

    @DeleteMapping("/{articleId}/comment/{id}")
    public String deleteArticleComment(@PathVariable Long articleId, @PathVariable Long id) {
       String loginUserId = authService.getLoginUserId();
        if (commentService.isAuthor(id, loginUserId)) {
            commentService.deleteComment(id);
            return "redirect:/article/show/" + articleId;
        }
        throw new CommentAuthorMismatchException();
    }
}
