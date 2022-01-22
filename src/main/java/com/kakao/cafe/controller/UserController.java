package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.CreateUserDto;
import com.kakao.cafe.dto.user.LoginDto;
import com.kakao.cafe.dto.user.ShowUserDto;
import com.kakao.cafe.dto.user.UpdateUserDto;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.util.consts.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.findAllUser());
        return "users/list";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute @Validated CreateUserDto createUserDto, RedirectAttributes attr) {
        userService.join(createUserDto);
        log.info("Create User - {}", createUserDto);
        attr.addFlashAttribute("success", true);
        attr.addFlashAttribute("successMessage", "회원 가입이 정상적으로 처리되었습니다.");
        return "redirect:/";
    }

    @GetMapping("/users/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model) {
        ShowUserDto profile = userService.findProfile(userId);
        model.addAttribute("user", profile);
        return "users/profile";
    }

    @GetMapping("/users/me/form")
    public String updateUserForm(Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) ShowUserDto loginUser) {
        model.addAttribute("user", loginUser);
        return "users/editForm";
    }

    @PutMapping("/users/{userId}")
    public String updateUser(@PathVariable String userId, @ModelAttribute @Validated UpdateUserDto updateUserDto) {
        ShowUserDto editUser = userService.updateProfile(userId, updateUserDto);
        log.info("Update User - {}", editUser);
        return "redirect:/users";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto, HttpSession session) {
        ShowUserDto loginUser = userService.login(loginDto);
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
