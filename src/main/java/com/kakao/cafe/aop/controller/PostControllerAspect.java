package com.kakao.cafe.aop.controller;

import com.kakao.cafe.service.PostService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class PostControllerAspect {

    private final PostService postService;

    @AfterReturning("execution(* com.kakao.cafe.controller.PostController.postDetailView(..)) && args(postId,..)")
    public void increaseViewNumOfPost(Long postId) {
        postService.increaseViewNumById(postId);
    }
}
