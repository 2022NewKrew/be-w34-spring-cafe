package com.kakao.cafe.controller;

import com.kakao.cafe.service.BoardService;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;

    @GetMapping("/list")
    public String getBoardList() {
        return "/index";
    }
}
