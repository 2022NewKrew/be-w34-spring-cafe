package com.kakao.cafe.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(UserDTO userDTO) {
        userService.save(userDTO);
        return "redirect:/users";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        UserDTO userDTO = userService.findById(userId);
        if (userDTO.getPassWord().equals(password)) {
            session.setAttribute("sessionedUser", userDTO);  // userDTO가 아니라 user가 돼야되나? 수정해볼것...
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


    @GetMapping("")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll().getUsers());
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String getProfile(@PathVariable String userId, Model model) {
        System.out.println(userId);
        model.addAttribute("user", userService.findById(userId));
        return "user/profile";
    }

}

