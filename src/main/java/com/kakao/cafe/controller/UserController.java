package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserSaveDto;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public String save(@ModelAttribute() UserSaveDto userSaveDto) {
        userService.save(userSaveDto);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }
}
