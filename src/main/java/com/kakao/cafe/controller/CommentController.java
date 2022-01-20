package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Entity.Comment;
import com.kakao.cafe.domain.Entity.User;
import com.kakao.cafe.dto.user.UserInfoDto;
import com.kakao.cafe.exceptions.WrongAccessException;
import com.kakao.cafe.service.reply.CommentService;
import com.kakao.cafe.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;
    private UserService userService;

    @PostMapping("/articles/{articleId}/comments/post")
    public String postComment(String contents, HttpSession session, HttpServletRequest request, @PathVariable int articleId) {
        String referer = request.getHeader("Referer");
        UserInfoDto user = (UserInfoDto) session.getAttribute("sessionedUser");
        String userId = user.getUserId();
        String writer = user.getName();
        this.commentService.save(new Comment(articleId, userId, writer, contents));
        return "redirect:" + referer;
    }

    @DeleteMapping("/articles/{articleId}/comments/{commentId}")
    public String deleteComment(HttpSession session, HttpServletRequest request, @PathVariable int articleId, @PathVariable int commentId) throws WrongAccessException {
        String userId = this.commentService.findById(commentId).getUserId();
        this.userService.userValidation(userId, session);
        String referer = request.getHeader("Referer");
        this.commentService.delete(commentId);
        return "redirect:" + referer;
    }
}
