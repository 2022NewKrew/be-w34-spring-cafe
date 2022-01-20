package com.kakao.cafe.controller;

import com.kakao.cafe.dto.PostCreateRequest;
import com.kakao.cafe.dto.PostDetailDto;
import com.kakao.cafe.dto.PostUpdateRequest;
import com.kakao.cafe.model.User;
import com.kakao.cafe.service.PostService;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String getPostList(Model model) {
        model.addAttribute("postList", postService.getAllPostDetail());
        return "posts/list";
    }

    @PostMapping
    public String write(@Valid PostCreateRequest requestDto, HttpSession session) {
        postService.write(requestDto, (User) session.getAttribute("sessionUser"));
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable("id") UUID id, HttpSession session, Model model) {
        PostDetailDto postDetailDto = postService.getPostDetailById(id);
        model.addAttribute("post", postDetailDto);

        UUID sessionUserId = ((User) session.getAttribute("sessionUser")).getId();
        model.addAttribute("isWriter", postService.isWriter(postDetailDto.getWriterId(), sessionUserId));

        return "posts/detail";
    }

    @PutMapping("/{id}")
    public String updatePost(@PathVariable("id") UUID id, @Valid PostUpdateRequest requestDto, HttpSession session) {
        UUID sessionUserId = ((User) session.getAttribute("sessionUser")).getId();
        postService.update(id, requestDto, sessionUserId);

        return "redirect:/posts/" + id;
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") UUID id, HttpSession session) {
        UUID sessionUserId = ((User) session.getAttribute("sessionUser")).getId();
        postService.delete(id, sessionUserId);

        return "redirect:/";
    }

    @GetMapping("{id}/update")
    public String getPostUpdateForm(@PathVariable("id") UUID id, Model model) {
        PostDetailDto postDetailDto = postService.getPostDetailById(id);
        model.addAttribute("post", postDetailDto);

        return "posts/update-form";
    }
}
