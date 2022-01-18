package com.kakao.cafe.controller.user;

import com.kakao.cafe.controller.user.dto.UserLoginDto;
import com.kakao.cafe.service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(UserLoginDto userLoginDto, HttpSession session) {
        userService.login(userLoginDto.getUserId(), userLoginDto.getPassword());
        session.setAttribute("loginUserId", userLoginDto.getUserId());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
