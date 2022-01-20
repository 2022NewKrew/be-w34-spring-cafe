package com.kakao.cafe.user.presentation;

import com.kakao.cafe.user.application.UserService;
import com.kakao.cafe.user.application.dto.UserLoginRequest;
import com.kakao.cafe.user.domain.SessionedUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static com.kakao.cafe.user.presentation.AuthController.AUTH_URI;

@Controller
@Slf4j
@RequestMapping(AUTH_URI)
public class AuthController {

    private final UserService userService;

    public static final String AUTH_URI = "/auths";

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String loginById(UserLoginRequest userLoginRequest, HttpSession session) {
        log.info(this.getClass() + ": 회원 로그인");
        SessionedUser userLoginResponse = userService.loginById(userLoginRequest);
        session.setAttribute("sessionedUser", userLoginResponse);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        log.info(this.getClass() + ": 회원 로그아웃");
        session.invalidate();
        return "redirect:/";
    }
}
