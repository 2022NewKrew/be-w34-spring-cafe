package com.kakao.cafe.controller;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.dto.PostDto;
import com.kakao.cafe.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    @GetMapping("/")
    public String postHome(Model model){
        model.addAttribute("posts", postService.findAllPost());

        return "index";
    }

    @PostMapping("/post")
    public String addPost(@ModelAttribute Post post){
        postService.createPost(post);
        return "redirect:/";
    }

    @GetMapping("/post/{postId}")
    public String detailPost(@PathVariable Long postId, Model model){
        Optional<Post> findOnePost = postService.findPost(postId);

        if(findOnePost.isEmpty()){
            throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
        }

        model.addAttribute("post", findOnePost.get());

        return "post/show";
    }
}
