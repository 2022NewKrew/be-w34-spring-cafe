package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService = new UserService();

    @PostMapping("/users")
    public String addUser(@ModelAttribute UserDto userDto) {

        logger.info("POST /users {}", userDto);

        userService.addUser(userDto);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        logger.info("GET /users");
        model.addAttribute("users", userService.findAll());
        model.addAttribute("countOfUser", userService.getCountOfUser());
        return "user/list";
    }

    @GetMapping("/users/{id}")
    public String findById(@PathVariable int id , Model model) {
        logger.info("GET /users/{}", id);
        model.addAttribute("user",userService.findById(id));
        return "user/profile";
    }

    @GetMapping("/users/{id}/form")
    public String editUserPage(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/updateForm";
    }

    @PostMapping("/users/{id}/update")
    public String editUser(@PathVariable int id, @ModelAttribute UserDto userDto){
        userService.updateUser(id, userDto);
        return "redirect:/users";
    }


}
