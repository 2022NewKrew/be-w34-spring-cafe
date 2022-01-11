package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("users/list")
    public ModelAndView userList(@RequestParam(defaultValue = "1") Integer pageNum, ModelAndView mav) {
        return mav;
    }
}
