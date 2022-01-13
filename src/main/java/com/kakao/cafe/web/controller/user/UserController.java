package com.kakao.cafe.web.controller.user;

import com.kakao.cafe.service.user.UserService;
import com.kakao.cafe.web.dto.user.UsersSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @PostMapping("/users")
    public String add(@ModelAttribute() UsersSaveRequestDto userDto) {
        userService.add(userDto);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/profile";
    }
}
