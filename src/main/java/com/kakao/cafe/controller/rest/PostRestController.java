package com.kakao.cafe.controller.rest;

import com.kakao.cafe.aop.login.LoginIdSessionNotNull;
import com.kakao.cafe.constant.RedirectedURL;
import com.kakao.cafe.dto.post.AddPostDto;
import com.kakao.cafe.dto.post.UpdatePostDto;
import com.kakao.cafe.service.PostService;
import com.kakao.cafe.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;

    private final HttpSession session;

    @PostMapping("/auth/posts/add")
    @LoginIdSessionNotNull
    public void addPost(AddPostDto addPostDto, HttpServletResponse response) throws IOException {
        Long writerId = SessionUtil.getLoginUserId(session);
        postService.addPost(addPostDto, writerId);

        response.sendRedirect(RedirectedURL.AFTER_WRITE_POST);
    }

    @PutMapping("/auth/posts/update")
    @LoginIdSessionNotNull
    public void updatePost(UpdatePostDto updatePostDto, HttpServletResponse response) throws IOException {
        Long loginUserId = SessionUtil.getLoginUserId(session);
        postService.updatePost(updatePostDto, loginUserId);

        response.sendRedirect(RedirectedURL.AFTER_UPDATE_POST);
    }

    @DeleteMapping("/auth/posts/delete")
    @LoginIdSessionNotNull
    public void deletePost(Long postId, HttpServletResponse response) throws IOException {
        Long loginUserId = SessionUtil.getLoginUserId(session);
        postService.deleteByIdAndUserId(postId, loginUserId);

        response.sendRedirect(RedirectedURL.AFTER_DELETE_POST);
    }
}
