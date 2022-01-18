package com.kakao.cafe.controller;

import com.kakao.cafe.dto.post.PostViewDto;
import com.kakao.cafe.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/{postId}")
    public ModelAndView postDetailView(@PathVariable("postId") Long postId, ModelAndView mav) {
        PostViewDto postViewDto = postService.findPostViewDtoById(postId);
        mav.addObject("postViewDto", postViewDto);

        mav.setViewName("postDetail");

        return mav;
    }
}
