package com.kakao.cafe.controller;

import com.kakao.cafe.dto.RequestUserDto;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService = new UserService();

    @PostMapping("/users")
    public String join(@ModelAttribute RequestUserDto userDto) {

        logger.info("POST /users {}", userDto);

        userService.join(userDto);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        logger.info("GET /users");
        model.addAttribute("users", userService.findUsers());
        model.addAttribute("countOfUser", userService.getCountOfUser());
        return "user/list";
    }

    @GetMapping("/users/{id}")
    public String getUserProfile(@PathVariable int id , Model model) {
        logger.info("GET /users/{}", id);
        model.addAttribute("user",userService.findOne(id));
        return "user/profile";
    }

    @GetMapping("/users/{id}/form")
    public String showEditUserPage(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "user/updateForm";
    }

    @PostMapping("/users/{id}/update")
    public String editUser(@PathVariable int id, @ModelAttribute RequestUserDto userDto){
        userService.updateUser(id, userDto);
        return "redirect:/users";
    }


}
