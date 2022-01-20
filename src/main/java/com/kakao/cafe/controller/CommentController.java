package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/articles/{articleId}/comment")
    public String createComment(@PathVariable long articleId, String contents, HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        Comment comment = new Comment(articleId, user.getName(), contents);

        commentService.save(comment);
        return "redirect:/articles/" + articleId;
    }
}
