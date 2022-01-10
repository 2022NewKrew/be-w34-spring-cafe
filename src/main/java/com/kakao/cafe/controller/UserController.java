package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.UserSignUpRequestDto;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public String signUp(UserSignUpRequestDto userSignUpRequestDto) {
        userService.singUp(userSignUpRequestDto);
        return "redirect:/users";
    }

}
