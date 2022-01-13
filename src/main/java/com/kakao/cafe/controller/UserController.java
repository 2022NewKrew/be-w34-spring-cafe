package com.kakao.cafe.controller;

import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.model.service.UserService;
import com.kakao.cafe.util.exception.UserDuplicatedException;
import com.kakao.cafe.util.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String goRegisterView() {
        return "user/register";
    }

    @PostMapping("/register")
    public String userRegister(UserDto userDto, Model model) {
        try {
            userService.registerUser(userDto);
        } catch (UserDuplicatedException e) {
            model.addAttribute("user", userDto);
            model.addAttribute("idErrorMessage", e.getMessage());
            return "user/register";
        }
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "user/list";
    }

    @GetMapping("/view/{userId}")
    public String userView(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user", userService.findUserByUserId(userId));
        return "user/view";
    }

    @PostMapping("/modify")
    public String goUserModifyView(String userId, Model model) {
        model.addAttribute("user", userService.findUserByUserId(userId));
        return "user/modify";
    }

    @PutMapping("/modify")
    public String userModify(UserDto userDto, String newPassword, Model model) {
        try {
            userService.findUserByLoginInfo(userDto.getUserId(), userDto.getPassword(), "비밀번호가 일치하지 않습니다.");
        } catch (UserNotFoundException e) {
            model.addAttribute("user", userDto);
            model.addAttribute("notMatchedErrorMessage", e.getMessage());
            return "user/modify";
        }

        userService.modifyUser(UserDto.builder()
                .userId(userDto.getUserId())
                .password(newPassword)
                .name(userDto.getName())
                .email(userDto.getEmail()).build());
        return "redirect:/user/list";
    }
}
