package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.UserProfileResponseDto;
import com.kakao.cafe.controller.dto.UserQueryResponseDto;
import com.kakao.cafe.controller.dto.UserSignUpRequestDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public String getAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users.stream()
                .map(UserQueryResponseDto::new)
                .collect(Collectors.toUnmodifiableList()));

        return "user/list";
    }

    @GetMapping("/profiles/{userId}")
    public String getUserProfile(@PathVariable("userId") String userId, Model model) {
        User foundUser = userService.findUserByUserId(userId);
        UserProfileResponseDto userProfileResponseDto = new UserProfileResponseDto(foundUser);
        model.addAttribute("user", userProfileResponseDto);
        return "user/profile";
    }

    @PostMapping()
    public String signUp(@ModelAttribute UserSignUpRequestDto userSignUpRequestDto) {
        userService.signUp(userSignUpRequestDto);
        return "redirect:/users";
    }

}
