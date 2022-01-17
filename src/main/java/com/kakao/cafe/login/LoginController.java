package com.kakao.cafe.login;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.login.dto.UserLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserLogin userLogin, HttpSession httpSession, Model model) {
        try {
            User user = loginService.login(userLogin);
            httpSession.setAttribute(SessionConfig.LOGIN_COOKIE, user);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("messages", Arrays.asList("아이디나 비밀번호가 일치하지 않습니다."));
            return "redirect:/users/login";
        }
    }
}
