package com.kakao.cafe.controller;

import com.kakao.cafe.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final List<User> userList = new ArrayList<>();

    @GetMapping
    public String getUserList(Model model) {
        logger.info("[GET] 유저 리스트 페이지");

        model.addAttribute("userList", userList);

        return "user/list";
    }

    @PostMapping
    public String signUp(User user) {
        logger.info("[POST] 회원가입 => 회원 정보 {}", user.toString());

        userList.add(user);

        return "redirect:/user";
    }

    @GetMapping("/{id}")
    public String profile(@PathVariable Long id, Model model) {
        logger.info("[GET] 회원 프로필 페이지");

        User findUser = userList.stream()
                .filter(u -> id.equals(u.getId()))
                .collect(Collectors.toList())
                .get(0);
        model.addAttribute("user", findUser);

        return "/user/profile";
    }
}
