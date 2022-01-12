package com.kakao.cafe.controller;

import com.kakao.cafe.model.post.PostDto;
import com.kakao.cafe.model.post.PostWriteRequest;
import com.kakao.cafe.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public String uploadPost(@Valid PostWriteRequest post, Errors errors, RedirectAttributes rttr) {
        if (errors.hasFieldErrors()) {
            errors.getFieldErrors().forEach(error -> rttr.addFlashAttribute(error.getField(), error.getDefaultMessage()));
            return "redirect:/posts/write";
        }
        try {
            postService.writePost(post);
        } catch (Exception e) {
            e.printStackTrace();
            rttr.addFlashAttribute("msg", e.getMessage());
            return "redirect:/posts/write";
        }
        rttr.addFlashAttribute("msg", "게시글을 등록하였습니다.");
        return "redirect:/";
    }

    @GetMapping("/")
    public String getAllPosts(Model model) {
        List<PostDto> posts = postService.getAllPosts();
        model.addAttribute("sizeOfPosts", posts.size());
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model, RedirectAttributes rttr) {
        try {
            model.addAttribute("post", postService.getPostById(id));
        } catch (Exception e) {
            e.printStackTrace();
            rttr.addFlashAttribute("msg", e.getMessage());
            return "redirect:/";
        }
        return "post/show";
    }

}
