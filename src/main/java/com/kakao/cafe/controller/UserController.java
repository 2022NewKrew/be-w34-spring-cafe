package com.kakao.cafe.controller;

import com.kakao.cafe.domain.dtos.UserSaveDto;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/create")
    public String createUserPage() {
        return "user/form";
    }

    @GetMapping("/users")
    public String userListPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute UserSaveDto dto) {
        userService.save(dto);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String userDetailPage(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/profile";
    }

    @GetMapping("/users/{id}/form")
    public String userUpdatePage(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/updateForm";
    }

    @PutMapping("/users/{id}")
    public String updateUser(@ModelAttribute UserSaveDto dto, @PathVariable long id) {
        userService.update(id, dto);
        return "redirect:/users";
    }
}
