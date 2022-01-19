package com.kakao.cafe.controller;

import com.kakao.cafe.Service.UserService;
import com.kakao.cafe.Dto.User.UserCreateRequestDto;
import com.kakao.cafe.Dto.User.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @GetMapping
    public String getUserList(Model model) {
        logger.info("[GET] 유저 리스트 페이지");

        List<UserResponseDto> userList = userService.getUserList();
        model.addAttribute("userList", userList);
        model.addAttribute("userNum", userList.size());

        return "user/list";
    }

    @PostMapping
    public String signUp(UserCreateRequestDto user) {
        logger.info("[POST] 회원가입 => 회원 정보 {}", user.toString());

        userService.signUp(user);

        return "redirect:/user";
    }

    @GetMapping("/{id}")
    public String profile(@PathVariable Long id, Model model) {
        logger.info("[GET] 회원 프로필 페이지");

        UserResponseDto findUser = userService.findUserById(id);
        model.addAttribute("user", findUser);

        return "user/profile";
    }
}
