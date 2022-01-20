package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserRegistrationDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model) {
        List<User> userList = userService.getUsers();
        model.addAttribute("users", userList);
        return "user/list";
    }

    @PostMapping
    public String createUser(@Valid UserRegistrationDto userDto) {
        userService.join(userDto);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String user(@PathVariable Integer id, Model model, HttpSession httpSession) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/profile";
    }
}