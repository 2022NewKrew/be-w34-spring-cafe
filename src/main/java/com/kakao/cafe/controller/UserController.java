package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.SignUpDto;
import com.kakao.cafe.exceptions.NoSuchUserException;
import com.kakao.cafe.exceptions.UserIdDuplicationException;
import com.kakao.cafe.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    // 회원 목록 페이지
    @GetMapping("/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", this.userService.getUserList());
        return "user/list";
    }

    // 회원 정보 페이지
    @GetMapping("/users/{userId}")
    public String showUser(@PathVariable String userId, Model model) {
        try {
            model.addAttribute("user", this.userService.getUserByUserId(userId));
            return "user/profile";
        } catch (NoSuchUserException e) {
            return "error";
        }
    }

    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String signUp() {
        return "user/form";
    }

    // 회원 가입 로직
    @PostMapping("/user/signup")
    public String addNewUser(SignUpDto signUpDto) {
        log.info("{}", signUpDto.getUserId());
        log.info("{}", signUpDto.getPassword());
        log.info("{}", signUpDto.getName());
        log.info("{}", signUpDto.getEmail());
        try {
            this.userService.saveNewUser(signUpDto);
            return "redirect:/users";
        } catch (UserIdDuplicationException e) {
            return "error";
        }
    }

    // 회원정보 수정 페이지
    @GetMapping("users/{userId}/form")
    public String updateUserInfo(@PathVariable String userId, Model model) {
        try {
            model.addAttribute("user", this.userService.getUserByUserId(userId));
            return "user/updateForm";
        } catch (NoSuchUserException e) {
            return "error";
        }
    }
}
