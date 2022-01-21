package com.kakao.cafe.thread.controller;

import com.kakao.cafe.exception.InvalidFormatException;
import com.kakao.cafe.thread.dto.CommentCreationForm;
import com.kakao.cafe.thread.dto.PostCreationForm;
import com.kakao.cafe.thread.service.PostService;
import com.kakao.cafe.user.dto.LoggedInUser;
import com.kakao.cafe.user.interceptor.NeedLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @NeedLogin
    @PostMapping
    public String processCreationForm(@Validated PostCreationForm postCreationForm, BindingResult bindingResult,
                                      @RequestAttribute LoggedInUser loggedInUser) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFormatException();
        }

        Long postId = postService.addFromForm(loggedInUser.getId(), postCreationForm);
        return "redirect:posts/" + postId;
    }

    @GetMapping
    public String listPosts(Model model) {
        model.addAttribute("posts", postService.getAll());
        return "post/list";
    }

    @GetMapping("/{id:[0-9]+}")
    public String showPost(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.get(id));
        return "post/detail";
    }

    @NeedLogin
    @GetMapping("/{id:[0-9]+}/edit")
    public String showPostEditor(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.get(id));
        return "post/post-edit-form";
    }

    @NeedLogin
    @PutMapping("/{id:[0-9]+}")
    public String editPost(@PathVariable Long id, @Validated PostCreationForm postCreationForm, BindingResult bindingResult,
                           @RequestAttribute LoggedInUser loggedInUser) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFormatException();
        }
        postService.updateFromForm(loggedInUser.getId(), id, postCreationForm);
        return "redirect:/posts/" + id;
    }

    @NeedLogin
    @DeleteMapping("/{id:[0-9]+}")
    public String deletePost(@PathVariable Long id, @RequestAttribute LoggedInUser loggedInUser) {
        postService.softDelete(loggedInUser.getId(), id);
        return "redirect:/posts";
    }

    @NeedLogin
    @PostMapping("/{id:[0-9]+}/comments")
    public String addComment(@PathVariable Long id, @Validated CommentCreationForm commentCreationForm, BindingResult bindingResult,
                             @RequestAttribute LoggedInUser loggedInUser) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFormatException();
        }
        postService.addCommentToPost(loggedInUser.getId(), id, commentCreationForm);
        return "redirect:/posts/" + id;
    }

    @NeedLogin
    @PutMapping("/{postId:[0-9]+}/comments/{commentId:[0-9]+}")
    public String editComment(@PathVariable Long postId, @PathVariable Long commentId, @Validated CommentCreationForm commentCreationForm, BindingResult bindingResult,
                             @RequestAttribute LoggedInUser loggedInUser) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFormatException();
        }
        postService.updateComment(loggedInUser.getId(), commentId, commentCreationForm);
        return "redirect:/posts/" + postId;
    }

    @NeedLogin
    @DeleteMapping("/{postId:[0-9]+}/comments/{commentId:[0-9]+}")
    public String editComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestAttribute LoggedInUser loggedInUser) {
        postService.softDeleteComment(loggedInUser.getId(), commentId);
        return "redirect:/posts/" + postId;
    }
}
