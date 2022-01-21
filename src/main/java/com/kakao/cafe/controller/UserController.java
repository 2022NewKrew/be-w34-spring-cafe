package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.LoginDto;
import com.kakao.cafe.dto.user.SignUpDto;
import com.kakao.cafe.dto.user.UserInfoDto;
import com.kakao.cafe.exceptions.NoSuchUserException;
import com.kakao.cafe.exceptions.PasswordMismatchException;
import com.kakao.cafe.exceptions.UserIdDuplicationException;
import com.kakao.cafe.exceptions.WrongAccessException;
import com.kakao.cafe.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    // 회원 목록 페이지
    @GetMapping("/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", this.userService.findAll());
        return "user/list";
    }

    // 회원 정보 페이지
    @GetMapping("/users/{userId}")
    public String showUser(@PathVariable String userId, Model model) throws NoSuchUserException {
        model.addAttribute("user", this.userService.findById(userId));
        return "user/profile";
    }

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signUpForm() {
        return "user/form";
    }

    // 회원 가입 요청
    @PostMapping("/signup")
    public String signUp(SignUpDto signUpDto) throws UserIdDuplicationException {
        this.userService.save(signUpDto);
        return "redirect:/articles";
    }

    // 회원정보 수정 페이지
    @GetMapping("users/{userId}/form")
    public String updateForm(@PathVariable String userId, Model model, HttpSession session) throws NoSuchUserException, WrongAccessException {
        this.userService.userValidation(userId, session);
        model.addAttribute("user", this.userService.findById(userId));
        return "user/updateForm";
    }

    // 회원정보 수정 요청
    @PatchMapping("users/{userId}/update")
    public String updateUserInfo(SignUpDto signUpDto, @PathVariable String userId, HttpSession session) throws PasswordMismatchException, NoSuchUserException, WrongAccessException {
        this.userService.userValidation(userId, session);
        this.userService.update(signUpDto);
        return "redirect:/articles";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginForm() {
        return "user/login";
    }

    // 로그인
    @PostMapping("/login")
    public String login(LoginDto loginDto, HttpSession session) throws PasswordMismatchException, NoSuchUserException {
        UserInfoDto userInfoDto = this.userService.login(loginDto);
        session.setAttribute("sessionedUser", userInfoDto);
        return "redirect:/articles";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        this.userService.logout(session);
        return "redirect:/users";
    }
}
