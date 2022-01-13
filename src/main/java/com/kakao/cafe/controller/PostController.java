package com.kakao.cafe.controller;

import com.kakao.cafe.domain.post.Posts;
import com.kakao.cafe.dto.PostDto;
import com.kakao.cafe.service.PostService;
import com.kakao.cafe.service.PostWriteService;
import com.kakao.cafe.util.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostController {

    private final PostService postService;
    private final PostWriteService postWriteService;

    @Autowired
    public PostController(PostService postService, PostWriteService postWriteService) {
        this.postService = postService;
        this.postWriteService = postWriteService;
    }

    @GetMapping("/")
    public String main() {
        return "redirect:/posts";
    }

    @GetMapping("/posts")
    public String postList(Model model) {
        Posts posts = postService.findAll();
        List<PostDto> postDtoList = posts.getPosts().stream()
                .map(PostMapper::toDto)
                .collect(Collectors.toList());
        model.addAttribute("posts", postDtoList);
        return "qna/posts";
    }

    @GetMapping("/questions")
    public String question() {
        return "qna/form";
    }

    @PostMapping("/post")
    public String makePost(PostDto postDto) {
        postWriteService.insertPost(PostMapper.toPost(postDto));
        return "redirect:/posts";
    }

    @GetMapping("/post/{idx}")
    public String getPost(@PathVariable int idx, Model model) {
        PostDto postDto = PostMapper.toDto(postService.findById(idx));
        model.addAttribute("post", postDto);
        return "qna/show";
    }

    @GetMapping("/posts/deleteAll")
    public String deleteAll() {
        postService.deleteAll();
        return "redirect:/posts";
    }

}
