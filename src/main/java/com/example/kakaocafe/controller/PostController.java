package com.example.kakaocafe.controller;

import com.example.kakaocafe.core.meta.URLPath;
import com.example.kakaocafe.core.meta.ViewPath;
import com.example.kakaocafe.domain.post.PostDAO;
import com.example.kakaocafe.domain.post.dto.Post;
import com.example.kakaocafe.domain.post.dto.WritePostForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/post")
@RequiredArgsConstructor
public class PostController {

    private final PostDAO postDAO;

    @PostMapping
    public ModelAndView create(WritePostForm writePostForm) {

        postDAO.create(writePostForm);

        return new ModelAndView(URLPath.INDEX.getRedirectPath());
    }

    @GetMapping(path = "/{postId}")
    public ModelAndView get(@PathVariable("postId") Long id) {

        final Post post = postDAO.getPostById(id)
                .orElseThrow();

        return new ModelAndView(ViewPath.SHOW_POST)
                .addObject("post", post);
    }
}
