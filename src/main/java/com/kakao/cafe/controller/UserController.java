package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserLoginResponse;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public String createUser(User user, HttpServletResponse response) {
        logger.info("입장 : {}", user.toString());

        final boolean status = userService.createUser(user);
        if (!status) {
            logger.info("이미 가입된 유저정보가 포함됨 : {}", user.toString());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "user/form";
        }

        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        final List<User> users = userService.getUsers();

        logger.info("유저 리스트 정보 : {}", users.toString());

        model.addAttribute("usersSize", users.size());
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String getUser(@PathVariable("userId") long userId, Model model) {
        final User user = userService.getUser(userId);

        logger.info("유저 정보 : {}", user.toString());

        model.addAttribute("user", user);
        return "user/profile";
    }

    // 뷰에서 로그인 기능 미구현으로 인해 추후 구현예정
    @PostMapping("/users/login")
    public String loginUser(UserLoginResponse userLoginResponse, HttpServletResponse response) {
        final User user = userService.loginUser(userLoginResponse);

        if (user == null) {
            logger.info("유저 정보가 존재하지 않음 : {}", userLoginResponse);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "user/login";
        }

        return "index";
    }
}
