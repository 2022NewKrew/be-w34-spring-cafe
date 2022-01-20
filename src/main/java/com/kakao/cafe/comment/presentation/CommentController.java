package com.kakao.cafe.comment.presentation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static com.kakao.cafe.article.presentation.ArticleController.ARTICLE_URI;
import static com.kakao.cafe.comment.presentation.CommentController.COMMENT_URI;

@Controller
@Slf4j
@RequestMapping(ARTICLE_URI + "{articleId}" + COMMENT_URI)
public class CommentController {

    public static final String COMMENT_URI = "/comments";

    @PostMapping
    public String save(@PathVariable String articleId) {
        log.info(this.getClass() + ": 댓글 작성");
        return "redirect:/comments";
    }

    @GetMapping
    public ModelAndView findAll(@PathVariable String articleId, Map<String, Object> model) {
        log.info(this.getClass() + ": 댓글 조회");
        return new ModelAndView();
    }

    @DeleteMapping("/{commentId}")
    public String deleteById(@PathVariable int articleId, @PathVariable String commentId) {
        log.info(this.getClass() + ": 댓글 삭제");
        return "redirect:/";
    }
}
