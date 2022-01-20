package com.kakao.cafe.controller;

import com.kakao.cafe.dto.post.CreatePostDto;
import com.kakao.cafe.dto.post.ShowPostDto;
import com.kakao.cafe.dto.post.UpdatePostDto;
import com.kakao.cafe.dto.reply.CreateReplyDto;
import com.kakao.cafe.dto.reply.ShowReplyDto;
import com.kakao.cafe.dto.user.ShowUserDto;
import com.kakao.cafe.service.PostService;
import com.kakao.cafe.service.ReplyService;
import com.kakao.cafe.util.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@Controller
@Slf4j
public class PostController {
    private final PostService postService;
    private final ReplyService replyService;

    @GetMapping("/")
    public String postHome(Model model) {
        List<ShowPostDto> posts = postService.findAllPost();
        model.addAttribute("posts", posts);
        model.addAttribute("postSize", posts.size());

        return "index";
    }

    @PostMapping("/posts")
    public String addPost(@ModelAttribute @Validated CreatePostDto postDto, HttpSession session) {
        ShowUserDto sessionUser = (ShowUserDto) session.getAttribute("sessionUser");
        postDto.setWriter(sessionUser.getUserId());

        ShowPostDto post = postService.createPost(postDto);
        log.info("Create Post - {}", postDto);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/{postId}")
    public String detailPost(@PathVariable Long postId, Model model) {
        model.addAttribute("post", postService.findPost(postId));
        model.addAttribute("replys", replyService.findAllReply(postId));
        return "posts/show";
    }

    @PostMapping("/posts/{postId}/reply")
    public String addReply(@PathVariable Long postId, @Valid @ModelAttribute CreateReplyDto replyDto, HttpSession session){
        ShowUserDto sessionUser = (ShowUserDto) session.getAttribute("sessionUser");

        replyDto.setPostId(postId);
        replyDto.setUserId(sessionUser.getUserId());

        replyService.createReply(replyDto);
        log.info("Create Reply - {}", replyDto);
        return "redirect:/posts/" + postId;
    }

    @DeleteMapping("/posts/{postId}/reply/{replyId}")
    public String deleteReply(@PathVariable Long postId, @PathVariable Long replyId, HttpSession session){
        replyUserCheck(replyId, session);

        replyService.deleteReply(replyId);
        return "redirect:/posts/" + postId;
    }

    @GetMapping("/posts/{postId}/form")
    public String updatePostForm(@PathVariable Long postId, Model model, HttpSession session) {
        postUserCheck(postId, session);

        ShowPostDto post = postService.findPost(postId);
        model.addAttribute("post", post);

        return "posts/editForm";
    }

    @PutMapping("/posts/{postId}")
    public String updatePost(@PathVariable Long postId, @ModelAttribute UpdatePostDto postDto, HttpSession session) {
        postUserCheck(postId, session);

        postDto.setWriter(session.getId());
        ShowPostDto showPostDto = postService.updatePost(postId, postDto);
        log.info("Update Post - {}", showPostDto);

        return "redirect:/posts/" + postId;
    }

    @DeleteMapping("/posts/{postId}")
    public String deletePost(@PathVariable Long postId, HttpSession session) {
        postUserCheck(postId, session);

        postService.deletePost(postId);
        return "redirect:/";
    }

    private void postUserCheck(Long postId, HttpSession session) {
        ShowUserDto sessionUser = (ShowUserDto) session.getAttribute("sessionUser");
        ShowPostDto post = postService.findPost(postId);

        if (!sessionUser.getUserId().equals(post.getWriter())) {
            throw new ForbiddenException("접근 권한이 없는 사용자 입니다.");
        }
    }

    private void replyUserCheck(Long replyId, HttpSession session){
        ShowUserDto sessionUser = (ShowUserDto) session.getAttribute("sessionUser");
        ShowReplyDto reply = replyService.findOneReply(replyId);

        if (!sessionUser.getUserId().equals(reply.getUserId())) {
            throw new ForbiddenException("접근 권한이 없는 사용자 입니다.");
        }
    }
}
