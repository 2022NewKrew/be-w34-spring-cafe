package com.kakao.cafe.controller.rest;

import com.kakao.cafe.constant.RedirectedURL;
import com.kakao.cafe.dto.post.AddPostDto;
import com.kakao.cafe.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;

    @PostMapping("/posts/add")
    public void addPost(AddPostDto addPostDto, HttpServletResponse response) throws IOException {
        Long writerId = Long.valueOf(1); // todo - 추후 로그인 구현 시 세션에서 유저 아이디 가져올 예정
        postService.addPost(addPostDto, writerId);

        response.sendRedirect(RedirectedURL.AFTER_WRITE_POST);
    }
}
