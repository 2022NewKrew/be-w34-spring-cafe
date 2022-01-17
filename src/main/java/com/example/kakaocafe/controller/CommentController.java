package com.example.kakaocafe.controller;

import com.example.kakaocafe.core.meta.URLPath;
import com.example.kakaocafe.domain.post.comment.CommentDAO;
import com.example.kakaocafe.domain.post.comment.CommentService;
import com.example.kakaocafe.domain.post.comment.dto.WriteCommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.print.DocFlavor;

@Controller
@RequestMapping(path = "/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ModelAndView create(WriteCommentForm writeCommentForm) {

        commentService.create(writeCommentForm);

        final long postId = writeCommentForm.getPostId();

        return new ModelAndView(URLPath.SHOW_POST.getRedirectPath() + postId);
    }

    @DeleteMapping(path = "/{commentId}")
    public ModelAndView delete(@PathVariable("postId") long postId,
                               @PathVariable("commentId") long commentId,
                               @SessionAttribute("userKey") long writerId) {

        commentService.delete(postId, commentId, writerId);

        return new ModelAndView(URLPath.SHOW_POST.getRedirectPath() + postId);
    }
}
