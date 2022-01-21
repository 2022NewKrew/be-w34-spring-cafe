package com.kakao.cafe.controller;

import com.kakao.cafe.domain.comment.Comments;
import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.Posts;
import com.kakao.cafe.dto.CommentWebDto;
import com.kakao.cafe.dto.PostDto;
import com.kakao.cafe.service.CommentService;
import com.kakao.cafe.service.PostService;
import com.kakao.cafe.service.PostWriteService;
import com.kakao.cafe.util.annotation.LoginCheck;
import com.kakao.cafe.util.exception.throwable.UnauthorizedActionException;
import com.kakao.cafe.util.mapper.CommentWebMapper;
import com.kakao.cafe.util.mapper.PostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostController {

    private final PostService postService;
    private final PostWriteService postWriteService;
    private final CommentService commentService;
    private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);

    @Autowired
    public PostController(PostService postService, PostWriteService postWriteService, CommentService commentService) {
        this.postService = postService;
        this.postWriteService = postWriteService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String main(HttpSession httpSession) {
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

    @LoginCheck
    @GetMapping("/questions")
    public String question() {
        return "qna/form";
    }


    @LoginCheck
    @GetMapping("/questions/{postId}")
    public String updateForm(@PathVariable int postId, Model model, HttpSession httpSession) {
        String curUserId = (String) httpSession.getAttribute("sessionId");
        Post post = postService.findById(postId);
        postService.canUpdate(curUserId, post);
        model.addAttribute("post", PostMapper.toDto(postService.findById(postId)));
        return "qna/updateForm";
    }

    @LoginCheck
    @PutMapping("/questions/{postId}")
    public String update(@PathVariable int postId, PostDto postDto, HttpSession httpSession) {
        LOGGER.info("update() : start");
        Post originalPost = postService.findById(postId);
        postDto.setWriter(originalPost.getWriter());
        postDto.setId(postId);
        String curUserId = (String) httpSession.getAttribute("sessionId");
        postService.update(PostMapper.toPost(postDto), curUserId);
        LOGGER.info("update() : end");
        return "redirect:/post/" + postId;
    }


    @LoginCheck
    @PostMapping("/post")
    public String makePost(PostDto postDto, HttpSession httpSession) {
        postDto.setWriter((String) httpSession.getAttribute("sessionId"));
        postWriteService.insertPost(PostMapper.toPost(postDto));
        return "redirect:/posts";
    }

    @LoginCheck
    @GetMapping("/post/{idx}")
    public String getPost(@PathVariable int idx, Model model) {
        PostDto postDto = PostMapper.toDto(postService.findById(idx));
        Comments comments = commentService.findAll(idx);
        List<CommentWebDto> commentWebDtos = comments.getComments().stream()
                .map(CommentWebMapper::toDto).collect(Collectors.toList());
        model.addAttribute("post", postDto);
        model.addAttribute("comments", commentWebDtos);
        return "qna/show";
    }

    @GetMapping("/posts/deleteAll")
    public String deleteAll() {
        postService.deleteAll();
        return "redirect:/posts";
    }

    @DeleteMapping("/posts/{postId}")
    public String delete(@PathVariable long postId, HttpSession httpSession) {
        try {
            postService.delete((String) httpSession.getAttribute("sessionId"), postId);
        } catch (UnauthorizedActionException unauthorizedActionException) {
            return "redirect:/users/login";
        }
        return "redirect:/posts";
    }

}
