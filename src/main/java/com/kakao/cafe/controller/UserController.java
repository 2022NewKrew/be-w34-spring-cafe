package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String login() {
        return "user/form";
    }

    // 회원 목록 페이지
    @GetMapping("/users")
    public String showUsers(Model model) {
        List<UserDto> users = this.userService.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }

    // 회원 가입 로직
    @PostMapping("/user/signup")
    public String addNewMember(@ModelAttribute UserDto userDto) {
        log.info("{}", userDto.getUserId());
        log.info("{}", userDto.getPassword());
        log.info("{}", userDto.getName());
        log.info("{}", userDto.getEmail());
        this.userService.saveNewUser(userDto);
        return "redirect:/users";
    }
}
