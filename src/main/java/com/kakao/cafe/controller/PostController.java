package com.kakao.cafe.controller;

import com.kakao.cafe.Dto.Login.LoginAuthDto;
import com.kakao.cafe.Dto.Post.DetailedPostResponseDto;
import com.kakao.cafe.Service.PostService;
import com.kakao.cafe.Dto.Post.PostRequestDto;
import com.kakao.cafe.Dto.Post.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qna")
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    @PostMapping
    public String createQuestion(String title, String content, HttpSession session) {
        logger.info("[POST] 게시글 작성 / title:{} content{}", title, content);

        LoginAuthDto authInfo = (LoginAuthDto) session.getAttribute("authInfo");
        postService.createQuestion(new PostRequestDto(authInfo.getUserId(), title, content));

        return "redirect:/";
    }

    @GetMapping("/form")
    public String questionForm(HttpSession session) {
        logger.info("[GET] 게시글 작성 양식 페이지");

        LoginAuthDto authInfo = (LoginAuthDto) session.getAttribute("authInfo");
        postService.chkLogin(authInfo);

        return "qna/form";
    }

    @GetMapping("/{id}")
    public String showQuestion(@PathVariable int id, Model model, HttpSession session) {
        logger.info("[GET] 게시글 {} 상세보기 페이지", id);

        LoginAuthDto authInfo = (LoginAuthDto) session.getAttribute("authInfo");
        postService.chkLogin(authInfo);

        DetailedPostResponseDto post = postService.findDetailedPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("userId", authInfo.getUserId());

        return "qna/show";
    }

    @GetMapping("/{id}/form")
    public String editForm(@PathVariable int id, HttpSession session, Model model) {
        logger.info("[GET] 게시글 {} 수정 페이지", id);

        LoginAuthDto authInfo = (LoginAuthDto) session.getAttribute("authInfo");
        PostResponseDto post = postService.findPostById(id);

        postService.chkAuth(authInfo, post);
        model.addAttribute("post", post);

        return "qna/editForm";
    }

    @PutMapping("/{id}")
    public String editPost(@PathVariable int id, String title, String content, HttpSession session) {
        logger.info("[PUT] 게시글 수정 / title:{} content:{}", title, content);

        LoginAuthDto authInfo = (LoginAuthDto) session.getAttribute("authInfo");
        postService.editQuestion(id, new PostRequestDto(authInfo.getUserId(), title, content));

        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable int id, HttpSession session) {
        logger.info("[DELETE] 게시글 삭제");

        LoginAuthDto authDto = (LoginAuthDto) session.getAttribute("authInfo");
        postService.deleteById(id, authDto);

        return "redirect:/";
    }
}
