package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.CreateUserDto;
import com.kakao.cafe.dto.user.LoginDto;
import com.kakao.cafe.dto.user.ShowUserDto;
import com.kakao.cafe.dto.user.UpdateUserDto;
import com.kakao.cafe.exception.UnAuthorizedException;
import com.kakao.cafe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    public String getUserList(Model model){
        model.addAttribute("users", userService.findAllUser());
        return "users/list";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute @Validated CreateUserDto createUserDto){
        userService.join(createUserDto);
        log.info("Create User - {}", createUserDto);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model){
        ShowUserDto profile = userService.findProfile(userId);
        model.addAttribute("user", profile);
        return "users/profile";
    }

    @GetMapping("/users/{userId}/form")
    public String userUpdateForm(@PathVariable String userId, Model model, HttpSession session){
        ShowUserDto sessionUser = (ShowUserDto) session.getAttribute("sessionUser");
        if(sessionUser == null || !sessionUser.getUserId().equals(userId)){
            // 401에러 반환하기
            throw new UnAuthorizedException("로그인이 필요합니다.");
        }

        model.addAttribute("user", sessionUser);
        return "users/editForm";
    }

    @PutMapping("/users/{userId}")
    public String userUpdate(@PathVariable String userId, @ModelAttribute @Validated UpdateUserDto updateUserDto){
        ShowUserDto editUser = userService.editProfile(userId, updateUserDto);
        log.info("Update User - {}", editUser);
        return "redirect:/users";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto, HttpSession session){
        ShowUserDto loginUser = userService.login(loginDto);
        session.setAttribute("sessionUser", loginUser);

        return "redirect:/";
    }
}
