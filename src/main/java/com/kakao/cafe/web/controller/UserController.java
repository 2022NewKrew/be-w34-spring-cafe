package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.UserDTO;
import com.kakao.cafe.web.service.UserService;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {
    private final UserService userService;
    private final Logger logger;

    UserController() {
        this.userService = new UserService();
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @GetMapping("/user/form")
    public String getUserForm() {
        return "/user/form";
    }

    @GetMapping("/user")
    public String getUserList(Model model) {
        model.addAttribute("userList", userService.getUserList());
        return "/user/list";
    }

    @GetMapping("/user/login")
    public String getLoginForm() { return "/user/login"; }

    @PostMapping("/user/create")
    public String createUser(String userId, String password, String name, String email) {
        UserDTO userDTO = new UserDTO(userId, password, name, email);
        System.out.println(userDTO.getUserId());
        logger.info(userDTO.toString());
        logger.info(userService.signUp(userDTO).toString());
        return "redirect:/user";
    }
}
