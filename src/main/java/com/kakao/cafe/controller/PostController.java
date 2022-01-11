package com.kakao.cafe.controller;

import com.kakao.cafe.dto.PageRequestDto;
import com.kakao.cafe.dto.PostDto;
import com.kakao.cafe.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/post")
    public String postBoardForm() {
        return "/board/post";
    }

    @PostMapping("/post")
    public String postBoard(PostDto postDto, HttpSession session) {
        postService.register(postDto);
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String getBoardList(PageRequestDto pageRequestDto, Model model) {
        model.addAttribute("posts", postService.getList(pageRequestDto));
        return "/board/list";
    }
}
