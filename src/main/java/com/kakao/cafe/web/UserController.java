package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final List<User> userList = new ArrayList<>();

    @GetMapping("/user")
    public String getUserList(User user, Model model) {
        logger.info("[GET] 유저 리스트 페이지");

        model.addAttribute("userList", userList);

        return "user/list";
    }

    @GetMapping("/user/form.html")
    public String form() {
        logger.info("[GET] 회원 가입 페이지");

        return "/user/form";
    }

    @PostMapping("/user/create")
    public String signUp(User user) {
        logger.info("[POST] 회원가입 => 회원 정보 {}", user.toString());

        userList.add(user);

        return "redirect:/user";
    }
}
