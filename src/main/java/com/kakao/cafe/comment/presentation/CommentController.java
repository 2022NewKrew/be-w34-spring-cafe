package com.kakao.cafe.comment.presentation;

import com.kakao.cafe.comment.application.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.kakao.cafe.article.presentation.ArticleController.ARTICLE_URI;
import static com.kakao.cafe.comment.presentation.CommentController.COMMENT_URI;

@Controller
@Slf4j
@RequestMapping(ARTICLE_URI + "{articleId}" + COMMENT_URI)
public class CommentController {

    public static final String COMMENT_URI = "/comments";

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    public String save(@PathVariable String articleId) {
        log.info(this.getClass() + ": 댓글 작성");
        return "redirect:/comments";
    }

    @DeleteMapping("/{commentId}")
    public String deleteById(@PathVariable int articleId, @PathVariable String commentId) {
        log.info(this.getClass() + ": 댓글 삭제");
        return "redirect:/";
    }
}
