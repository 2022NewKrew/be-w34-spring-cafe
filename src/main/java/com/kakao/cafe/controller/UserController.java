package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.dto.SignUpRequestDto;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UsersController {

    private UserService usersService;

    private static Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    public UsersController(UserService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = usersService.getUsersAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    @PostMapping("/users")
    public String signUp(SignUpRequestDto signUpRequestDto) {
        usersService.signUp(User.of(signUpRequestDto.getUserId(), signUpRequestDto.getPassword(), signUpRequestDto.getName(), signUpRequestDto.getEmail()));
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String profile(@PathVariable String userId, Model model) {
        User findUser = usersService.getUser(userId);
        model.addAttribute("user", findUser);
        logger.info(findUser.toString());
        return "user/profile";
    }
}
