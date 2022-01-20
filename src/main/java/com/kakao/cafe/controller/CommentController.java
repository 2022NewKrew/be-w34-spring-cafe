package com.kakao.cafe.controller;

import com.kakao.cafe.domain.comment.CommentService;
import com.kakao.cafe.domain.comment.dto.CommentRawDataDto;
import com.kakao.cafe.domain.comment.repository.JdbcTemplateCommentRepository;
import com.kakao.cafe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final JdbcTemplateCommentRepository commentRepository;
    private final CommentService commentService;

    @PostMapping("/articles/{articleId}/comments/")
    public String registerComment(@PathVariable Long articleId, @SessionAttribute("sessionedUser") User user, String contents) {
        CommentRawDataDto commentRawDataDto = new CommentRawDataDto(articleId, user.getId(), contents);
        commentRepository.save(commentRawDataDto);
        return "redirect:/articles/" + articleId;
    }

    @DeleteMapping("/articles/{articleId}/comments/{commentId}")
    public String deleteComment(@PathVariable("articleId") Long articleId, @PathVariable("commentId") Long commentId, @SessionAttribute("sessionedUser") User user) {
        commentService.deleteComment(commentId, user);
        return "redirect:/articles/" + articleId;
    }
}
