package com.kakao.cafe.controller;

import com.kakao.cafe.domain.comment.Comment;
import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.service.CommentService;
import com.kakao.cafe.service.PostService;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.util.annotation.LoginCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService, PostService postService) {
        this.commentService = commentService;
        this.userService = userService;
        this.postService = postService;
    }


    @LoginCheck
    @PostMapping("/post/{postId}/comment")
    public String insert(@PathVariable long postId, String text, HttpSession httpSession) {
        String userId = (String) httpSession.getAttribute("sessionId");
        Post post = postService.findById(postId);
        User user = userService.findById(userId);
        Comment comment = new Comment.Builder()
                .user(user)
                .post(post)
                .text(text)
                .build();
        commentService.insert(comment);
        return "redirect:/post/" + postId;
    }

    @LoginCheck
    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public String delete(@PathVariable long postId, @PathVariable long commentId, HttpSession httpSession) {
        String userId = (String) httpSession.getAttribute("sessionId");
        Comment comment = commentService.findById(commentId);
        commentService.delete(comment, userId);
        return "redirect:/post/" + postId;
    }

}
