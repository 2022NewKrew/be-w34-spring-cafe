package com.kakao.cafe.controller;

import com.kakao.cafe.core.meta.SessionData;
import com.kakao.cafe.domain.comment.CommentService;
import com.kakao.cafe.domain.comment.dto.CommentCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/articles/{articleId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("")
    public String create(CommentCreateDto dto) {
        final Long articleId = dto.getArticleId();
        commentService.create(dto);
        return "redirect:/articles/" + articleId;
    }

    @DeleteMapping("/{commentId}")
    public String delete(HttpSession session,
                         @PathVariable("articleId") Long articleId,
                         @PathVariable("commentId") Long commentId) {
        final Long userKey = (Long) session.getAttribute(SessionData.USER_KEY);
        commentService.delete(commentId, userKey);
        return "redirect:/articles/" + articleId;
    }
}
