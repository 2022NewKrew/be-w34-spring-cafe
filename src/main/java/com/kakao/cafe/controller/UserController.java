package com.kakao.cafe.controller;

import com.kakao.cafe.dto.PageRequestDto;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@Log4j2
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public String userList(PageRequestDto pageRequestDto, Model model) {
        model.addAttribute("users", userService.getList(pageRequestDto));
        return "user/list";
    }
}
