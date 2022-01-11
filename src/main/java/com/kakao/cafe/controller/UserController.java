package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import com.kakao.cafe.dto.UserCreationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final Logger logger;
    private final UserService userService;

    public UserController() {
        this.logger = LoggerFactory.getLogger(UserController.class);
        this.userService = new UserService();
    }

    @GetMapping("/users")
    public String getUserList(Model model) {
        model.addAttribute("userList", userService.findAllUsers());
        logger.info("{}",userService.findAllUsers());
        return "list";
    }

    @PostMapping("/users")
    public String createUser(UserCreationDTO userCreationDTO) {
        userService.join(userCreationDTO);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String getUserProfile(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id).get());
        return "profile";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        return "redirect:login";
    }

    @PatchMapping("/users/{id}")
    public String patchUserProfile(@PathVariable("id") Long id, Model model) {
        return "profile";
    }
}
