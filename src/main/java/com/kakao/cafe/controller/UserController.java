package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.UserQueryResponseDto;
import com.kakao.cafe.controller.dto.UserSignUpRequestDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users.stream()
                .map(UserQueryResponseDto::new)
                .collect(Collectors.toUnmodifiableList()));

        return "user/list";
    }

    @PostMapping("/users")
    public String signUp(@ModelAttribute UserSignUpRequestDto userSignUpRequestDto) {
        userService.singUp(userSignUpRequestDto);
        return "redirect:/users";
    }

}
