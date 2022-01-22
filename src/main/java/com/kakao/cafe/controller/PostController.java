package com.kakao.cafe.controller;

import com.kakao.cafe.dto.post.CreatePostDto;
import com.kakao.cafe.dto.post.ShowPostDto;
import com.kakao.cafe.dto.post.UpdatePostDto;
import com.kakao.cafe.dto.reply.CreateReplyDto;
import com.kakao.cafe.dto.reply.ShowReplyDto;
import com.kakao.cafe.dto.user.ShowUserDto;
import com.kakao.cafe.service.PostService;
import com.kakao.cafe.service.ReplyService;
import com.kakao.cafe.util.consts.SessionConst;
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
    public String addPost(@ModelAttribute @Validated CreatePostDto postDto, @SessionAttribute(name = SessionConst.LOGIN_USER) ShowUserDto loginUser) {
        ShowPostDto post = postService.createPost(postDto, loginUser.getUserId());
        log.info("Create Post - {}", postDto);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/{postId}")
    public String detailPost(@PathVariable Long postId, Model model) {
        model.addAttribute("post", postService.findPost(postId));
        model.addAttribute("replys", replyService.findAllReply(postId));
        return "posts/show";
    }

    @GetMapping("/posts/{postId}/form")
    public String updatePostForm(@PathVariable Long postId, Model model, @SessionAttribute(name = SessionConst.LOGIN_USER) ShowUserDto loginUser) {
        checkPostUser(postId, loginUser.getUserId());

        model.addAttribute("post", postService.findPost(postId));
        return "posts/editForm";
    }

    @PutMapping("/posts/{postId}")
    public String updatePost(@PathVariable Long postId, @ModelAttribute UpdatePostDto postDto, @SessionAttribute(name = SessionConst.LOGIN_USER) ShowUserDto loginUser) {
        checkPostUser(postId, loginUser.getUserId());
        ShowPostDto showPostDto = postService.updatePost(postId, postDto);
        log.info("Update Post - {}", showPostDto);

        return "redirect:/posts/" + postId;
    }

    @DeleteMapping("/posts/{postId}")
    public String deletePost(@PathVariable Long postId, @SessionAttribute(name = SessionConst.LOGIN_USER) ShowUserDto loginUser) {
        checkPostUser(postId, loginUser.getUserId());
        postService.deletePost(postId, loginUser.getUserId());
        return "redirect:/";
    }

    @PostMapping("/posts/{postId}/reply")
    public String addReply(@PathVariable Long postId, @Valid @ModelAttribute CreateReplyDto replyDto, @SessionAttribute(name = SessionConst.LOGIN_USER) ShowUserDto loginUser){
        replyService.createReply(replyDto, postId, loginUser.getUserId());
        log.info("Create Reply - {}", replyDto);
        return "redirect:/posts/" + postId;
    }

    @DeleteMapping("/posts/{postId}/reply/{replyId}")
    public String deleteReply(@PathVariable Long postId, @PathVariable Long replyId, @SessionAttribute(name = SessionConst.LOGIN_USER) ShowUserDto loginUser){
        checkReplyUser(replyId, loginUser.getUserId());

        replyService.deleteReply(replyId);
        return "redirect:/posts/" + postId;
    }

    private void checkPostUser(Long postId, String loginUser){
        ShowPostDto post = postService.findPost(postId);
        if(!post.getWriter().equals(loginUser)){
            throw new ForbiddenException("접근 권한이 없는 사용자 입니다.");
        }
    }

    private void checkReplyUser(Long replyId, String loginUser){
        ShowReplyDto reply = replyService.findOneReply(replyId);
        if(!reply.getUserId().equals(loginUser)){
            throw new ForbiddenException("접근 권한이 없는 사용자 입니다.");
        }
    }
}
