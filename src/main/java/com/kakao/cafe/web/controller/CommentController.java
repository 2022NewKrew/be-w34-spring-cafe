package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.domain.Comment;
import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.comment.CommentCreateRequestDto;
import com.kakao.cafe.web.exception.UnauthorizedException;
import com.kakao.cafe.web.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/articles/{articleId}/comments")
    public String createComment(CommentCreateRequestDto commentCreateRequestDto, @PathVariable Long articleId) {
        logger.info("POST /articles/{}/comments: request {}", articleId, commentCreateRequestDto);

        // comment 생성
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setContent(commentCreateRequestDto.getContent());
        comment.setWriter(commentCreateRequestDto.getWriter());
        commentService.write(comment);
        return "redirect:/articles/{articleId}";
    }

    @DeleteMapping("/articles/{articleId}/comments/{id}")
    public String deleteComment(@PathVariable Long articleId, @PathVariable Long id, HttpSession session) {
        // comment 찾기
        Comment comment = commentService.findComment(id);

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (!comment.getWriter().equals(sessionUser.getUserId())) {
            throw new UnauthorizedException("글을 삭제할 권한이 없습니다.");
        }

        // comment 삭제
        logger.info("DELETE /articles/{}/comments/{}: request comment delete", articleId, id);
        commentService.delete(id);
        return "redirect:/articles/{articleId}";

    }


}
