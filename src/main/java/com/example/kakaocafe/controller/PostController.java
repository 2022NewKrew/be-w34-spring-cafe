package com.example.kakaocafe.controller;

import com.example.kakaocafe.core.meta.URLPath;
import com.example.kakaocafe.core.meta.ViewPath;
import com.example.kakaocafe.domain.post.PostService;
import com.example.kakaocafe.domain.post.dto.Post;
import com.example.kakaocafe.domain.post.dto.PostInfo;
import com.example.kakaocafe.domain.post.dto.UpdatePostForm;
import com.example.kakaocafe.domain.post.dto.WritePostForm;
import com.example.kakaocafe.domain.user.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ModelAndView create(WritePostForm writePostForm) {
        final long id = postService.create(writePostForm);

        return new ModelAndView(URLPath.GET_POST.getRedirectPath() + id);
    }

    @GetMapping(path = "/{postId}")
    public ModelAndView get(@PathVariable("postId") Long id, Model model) {
        final Post post = postService.findPostByIdAndPlusViewCount(id);

        return new ModelAndView(ViewPath.SHOW_POST)
                .addObject("post", post)
                .addAllObjects(model.asMap());
    }

    @GetMapping(path = "/{postId}/updateForm")
    public ModelAndView showUpdateForm(@PathVariable("postId") Long postId,
                                       @SessionAttribute("userKey") Long writerId) {
        final PostInfo postInfo = postService.findPostInfoById(postId, writerId);

        return new ModelAndView(ViewPath.UPDATE_POST_FROM)
                .addObject("post", postInfo);
    }

    @PutMapping(path = "/{postId}")
    public ModelAndView update(UpdatePostForm updatePostForm) {
        postService.update(updatePostForm);

        final long postId = updatePostForm.getPostId();

        return new ModelAndView(URLPath.GET_POST.getRedirectPath() + postId);
    }

    @DeleteMapping(path = "/{postId}")
    public ModelAndView delete(@PathVariable("postId") Long id,
                               @SessionAttribute("userKey") long writerId) {

        postService.delete(id, writerId);

        return new ModelAndView(URLPath.INDEX.getRedirectPath());
    }
}
