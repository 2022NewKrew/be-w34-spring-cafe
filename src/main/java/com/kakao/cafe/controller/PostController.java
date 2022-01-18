package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.WritePostRequest;
import com.kakao.cafe.exceptions.InvalidWritePostException;
import com.kakao.cafe.service.PostService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    private final PostService postService;
    private final Logger logger = LoggerFactory.getLogger(PostController.class);

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public String write(@Valid WritePostRequest postDto, BindingResult errors) {
        logger.info("[POST] /posts 게시글 작성");
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .reduce("", (total, element) -> total + element + "\n");
            throw new InvalidWritePostException(errorMessage);
        }
        Post post = postDto.toEntity();
        postService.writePost(post);
        return "redirect:/";
    }

    @GetMapping
    public String postList(Model model) {
        logger.info("[GET] / 게시글 리스트");
        List<Post> postList = postService.getPostList();
        model.addAttribute("postList", postList);
        return "/index";
    }

    @GetMapping("/posts/{postId}")
    public String postById(@PathVariable int postId, Model model) {
        logger.info("[GET] /posts/{postId} 게시글 보기");
        Post post = postService.getPostById(postId);

        model.addAttribute("post", post);
        return "post/show";
    }
}
