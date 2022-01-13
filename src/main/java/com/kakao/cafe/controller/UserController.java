package com.kakao.cafe.controller;

import com.kakao.cafe.entity.User;
import com.kakao.cafe.entity.UserList;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;


@Controller
public class UserController {
    private Logger logger;
    private UserList userList;

    public UserController() {
        logger = LoggerFactory.getLogger(UserController.class);
        userList = new UserList();
    }


    @GetMapping("/users/login")
    public String getLoginPage() {
        this.logger.info("[GET] /user/login - 로그인 페이지 접속");
        return "user/login";
    }


    @GetMapping("/users/form")
    public String getFormPage() {
        this.logger.info("[GET] /user/form - 회원가입 페이지 접속");
        return "user/form";
    }

    @PostMapping("/users")
    public String signUp(User user, Model model) {
        logger.info("[Post] /users - 회원가입 요청");
        userList.addUser(user);
        return getUsersPage(model);
    }


    @GetMapping("/users")
    public String getUsersPage(Model model) {
        logger.info("[Get] /users - 유저 목록 요청");
        model.addAttribute("users", userList.getUserList());
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String getUserProfilePage(Model model, @PathVariable("userId") String userId) {

        logger.info("[GET] /user/{} - {} 유저 프로필 페이지 접속", userId, userId);
        model.addAttribute("user", userList.findUser(userId));
        return "user/profile";
    }
}
