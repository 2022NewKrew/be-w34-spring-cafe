package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserLoginRequest;
import com.kakao.cafe.exception.SaveFailException;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        try {
            userService.save(user);
        } catch (SaveFailException e) {
            logger.info("이미 가입된 유저정보가 포함됨 : {}", user.toString());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "user/form";
        }

        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        final List<User> users = userService.findAll();

        logger.info("유저 리스트 정보 : {}", users.toString());

        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String getUser(@PathVariable("userId") long userId, Model model) {
        final User user = userService.findByUserId(userId);

        logger.info("유저 정보 : {}", user.toString());

        model.addAttribute("user", user);
        return "user/profile";
    }

    @PostMapping("/login")
    public String loginUser(UserLoginRequest userLoginRequest, HttpServletResponse response, HttpSession session) {
        try {
            final User user = userService.findByEmailAndPassword(userLoginRequest);
            session.setAttribute("sessionedUser", user);

            return "redirect:/";
        } catch (EmptyResultDataAccessException e) {
            logger.info("유저 정보가 존재하지 않음 : {}", userLoginRequest);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            return "user/login";
        }
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
