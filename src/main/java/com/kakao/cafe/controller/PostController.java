package com.kakao.cafe.controller;

import com.kakao.cafe.Service.PostService;
import com.kakao.cafe.model.Article.PostCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qna")
public class PostController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final PostService postService;

    @PostMapping
    public String createQuestion(PostCreateRequestDto postCreateRequestDto) {
        logger.info("[POST] 게시글 등록 : {}", postCreateRequestDto.toString());

        postService.createQuestion(postCreateRequestDto);

        return "redirect:/";
    }
}
