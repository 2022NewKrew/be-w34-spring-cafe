package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.Id;
import com.kakao.cafe.domain.UserId;
import com.kakao.cafe.dto.CommentFormDto;
import com.kakao.cafe.service.CommentService;
import com.kakao.cafe.util.anotation.Login;
import com.kakao.cafe.util.mapper.CommentMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/questions/{questionId}/answers/create")
    public String createComments(@PathVariable Long questionId, @ModelAttribute CommentFormDto commentFormDto, @Login UserId loginId) {
        Comment comment = CommentMapper.toComment(commentFormDto);
        Id articleId = new Id(questionId);
        commentService.create(articleId, loginId, comment);
        return "redirect:/question/{questionId}";
    }

    @DeleteMapping("/questions/{questionId}/answers/{answerId}/delete")
    public String deleteComments(@PathVariable Long questionId, @PathVariable Long answerId, @Login UserId loginId) {
        Id articleId = new Id(questionId);
        Id commentId = new Id(answerId);
        commentService.delete(commentId, articleId, loginId);
        return "redirect:/question/{questionId}";
    }
}
