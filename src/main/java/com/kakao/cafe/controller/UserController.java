package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @GetMapping
    public String findUsers(Model model) {
        List<UserDto> users = userService.findAll();
        model.addAttribute("users", users);
        logger.info("GET /users: {}", users);
        return "/user/list";
    }

    @PostMapping
    public String signup(@ModelAttribute UserDto userDto) {
        int id = userService.create(userDto);
        logger.info("POST /users: {}", id);
        return "redirect:/users";
    }

    @GetMapping("{userId}")
    public String findUserOne(@PathVariable String userId, Model model) {
        UserDto user = userService.findByUserId(userId);
        model.addAttribute("user", user);
        logger.info("GET /users/{}: {}", user.getUserId(), user);
        return "/user/profile";
    }

    @GetMapping("form")
    public String showUpdateForm(Model model, HttpSession httpSession) {
        Object sessionId = httpSession.getAttribute("sessionId");
        UserDto user = userService.findByUserId(String.valueOf(sessionId));
        model.addAttribute("user", user);
        logger.info("GET /users/{}/form: {}", user.getUserId(), user);
        return "/user/userform";
    }

    @PutMapping
    public String updateUser(@ModelAttribute UserDto userDto, HttpSession httpSession) {
        Object sessionId = httpSession.getAttribute("sessionId");
        int id = userService.update(String.valueOf(sessionId), userDto);
        logger.info("PUT /users/{}: {}", id, userDto);
        return "redirect:/users";
    }




}
