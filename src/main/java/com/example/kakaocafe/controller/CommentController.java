package com.example.kakaocafe.controller;

import com.example.kakaocafe.core.meta.URLPath;
import com.example.kakaocafe.domain.post.comment.CommentDAO;
import com.example.kakaocafe.domain.post.comment.dto.WriteCommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/post/{postId}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentDAO commentDAO;

    @PostMapping
    public ModelAndView create(@RequestHeader("Referer") String redirectURL,
                               WriteCommentForm writeCommentForm) {

        commentDAO.create(writeCommentForm);

        return new ModelAndView("redirect:" + redirectURL);
    }
}
