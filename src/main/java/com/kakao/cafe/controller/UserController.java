package com.kakao.cafe.controller;

import com.kakao.cafe.domain.dtos.UserSaveDto;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUserPage() {
        return "user/form";
    }

    @GetMapping("")
    public String userListPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @PostMapping("")
    public String createUser(@ModelAttribute UserSaveDto dto) {
        userService.save(dto);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String userDetailPage(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/profile";
    }

    @GetMapping("/{id}/form")
    public String userUpdatePage(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/updateForm";
    }

    @PutMapping("/{id}")
    public String updateUser(@ModelAttribute UserSaveDto dto, @PathVariable Long id) {
        userService.update(id, dto);
        return "redirect:/users";
    }
}
