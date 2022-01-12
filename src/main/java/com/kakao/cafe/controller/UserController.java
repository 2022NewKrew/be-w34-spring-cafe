package com.kakao.cafe.controller;

import com.kakao.cafe.dto.PageRequestDto;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String userList(PageRequestDto pageRequestDto, Model model) {
        model.addAttribute("users", userService.getList(pageRequestDto));
        return "user/list";
    }
}
