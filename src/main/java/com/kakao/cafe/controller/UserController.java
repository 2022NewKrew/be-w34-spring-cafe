package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.UserFormDto;
import com.kakao.cafe.controller.dto.UserFormResponseDto;
import com.kakao.cafe.controller.dto.UserJoinDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute UserJoinDto userDto) {
        userService.join(userDto);
        return "redirect:/users";
    }


    @GetMapping({"/users", "users/list"})
    public String userList(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return "user/list";
    }

    @GetMapping("/users/{id}")
    public String userProfile(@PathVariable("id") String userId, Model model) {
        User user = userService.findUser(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/users/{id}/update")
    public String showForm(@PathVariable("id") String userId, Model model) {
        User user = userService.findUser(userId);
        UserFormResponseDto dtoUser = UserFormResponseDto.from(user);
        model.addAttribute("user", dtoUser);
        return "user/updateForm";
    }

    @PostMapping("/users/{id}/update")
    public String updateForm(@PathVariable("id") String userId, @ModelAttribute UserFormDto dto) {
        userService.updateUser(userId, dto);

        return "redirect:/users";
    }
}
