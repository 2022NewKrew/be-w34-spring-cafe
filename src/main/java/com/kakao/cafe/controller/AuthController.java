package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import com.kakao.cafe.dto.auth.LoginRequestDto;
import com.kakao.cafe.service.UserService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String requestSignupForm() {
        return "users/form";
    }

    @GetMapping("/login")
    public String requestLoginForm() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String requestLogin(@Valid LoginRequestDto dto, HttpSession session) {
        UserName userName = new UserName(dto.getUserName());
        Password password = new Password(dto.getPassword());
        User user = userService.findUserByLoginInfo(userName, password);
        session.setAttribute("loggedInUser", user);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String requestLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
