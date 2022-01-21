package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.SessionUser;
import com.kakao.cafe.domain.UpdatePostRequest;
import com.kakao.cafe.domain.WritePostRequest;
import com.kakao.cafe.exceptions.InvalidWritePostException;
import com.kakao.cafe.service.PostService;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class PostController {

    private static final String SESSION = "sessionUser";
    private final PostService postService;
    private final Logger logger = LoggerFactory.getLogger(PostController.class);

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public String write(@Valid WritePostRequest postDto, BindingResult errors, HttpSession session) {
        logger.info("[POST] /posts 게시글 작성");
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .reduce("", (total, element) -> total + element + "\n");
            throw new InvalidWritePostException(errorMessage);
        }
        SessionUser sessionUser = (SessionUser) session.getAttribute(SESSION);
        Post post = postDto.toEntity(sessionUser.getId());
        postService.writePost(post);
        return "redirect:/";
    }

    @GetMapping
    public String postList(Model model) {
        logger.info("[GET] / 게시글 리스트");
        List<Post> postList = postService.getPostList();
        model.addAttribute("postList", postList);
        return "post/list";
    }

    @GetMapping("/posts/{postId}")
    public String postById(@PathVariable int postId, Model model) {
        logger.info("[GET] /posts/{postId} 게시글 보기");
        Post post = postService.getPostById(postId);

        model.addAttribute("post", post);
        return "post/show";
    }

    @PutMapping("/posts/{postId}")
    public String postById(@Valid UpdatePostRequest postDto, @PathVariable int postId, Model model,
            HttpSession session) {
        logger.info("[PUT] /posts/{postId} 게시글 수정");
        SessionUser sessionUser = (SessionUser) session.getAttribute(SESSION);
        Post post = postDto.toEntity(sessionUser.getId(), postId);
        postService.updatePost(post);

        model.addAttribute("post", post);
        return "post/show";
    }

    @GetMapping("/posts/{postId}/update")
    public String updatePostGetContent(@PathVariable int postId, Model model) {
        logger.info("[GET] /posts/{postId}/update 게시글 수정 페이지");
        Post post = postService.getPostById(postId);

        model.addAttribute("post", post);
        return "post/form_update";
    }

    @DeleteMapping("/posts/{postId}")
    public String deletePost(@PathVariable int postId, HttpSession session) {
        logger.info("[DELETE] /posts/{postId} 게시글 삭제");

        SessionUser sessionUser = (SessionUser) session.getAttribute(SESSION);
        postService.deletePost(postId, sessionUser.getId());

        return "redirect:/";
    }
}
