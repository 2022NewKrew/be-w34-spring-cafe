package com.kakao.cafe.system;

import com.kakao.cafe.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by melodist
 * Date: 2022-01-17 017
 * Time: 오후 6:22
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final HttpSession session;

    @PostMapping("/login")
    public String login(@Valid LoginDto loginDto) {
        User loginUser = loginService.login(loginDto.getUserId(), loginDto.getPassword());

        if (loginUser == null) {
            return "/user/login_failed";
        }

        session.setAttribute("sessionedUser", loginUser);
        session.setMaxInactiveInterval(60);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
