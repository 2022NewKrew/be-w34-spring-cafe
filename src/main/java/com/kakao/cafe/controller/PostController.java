package com.kakao.cafe.controller;

import com.kakao.cafe.Dto.Login.LoginAuthDto;
import com.kakao.cafe.Service.PostService;
import com.kakao.cafe.Dto.Post.PostCreateRequestDto;
import com.kakao.cafe.Dto.Post.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qna")
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final PostService postService;

    @PostMapping
    public String createQuestion(PostCreateRequestDto postCreateRequestDto) {
        logger.info("[POST] 게시글 등록 : {}", postCreateRequestDto.toString());

        postService.createQuestion(postCreateRequestDto);

        return "redirect:/";
    }

    @GetMapping("/form")
    public String questionForm(HttpSession session) {
        logger.info("[GET] 게시글 작성 양식 페이지");

        LoginAuthDto authInfo = (LoginAuthDto) session.getAttribute("authInfo");
        if (authInfo == null) {
            logger.info("로그인 필요");
            return "redirect:/login";
        }
        return "qna/form";
    }

    @GetMapping("/{id}")
    public String showQuestion(@PathVariable Long id, Model model, HttpSession session) {
        logger.info("[GET] 게시글 {} 상세보기 페이지", id);

        LoginAuthDto authInfo = (LoginAuthDto) session.getAttribute("authInfo");
        if (authInfo == null) {
            logger.info("로그인 필요");
            return "redirect:/login";
        }

        PostResponseDto post = postService.findPostById(id);
        model.addAttribute("post", post);

        return "qna/show";
    }
}
