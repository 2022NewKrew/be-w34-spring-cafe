package com.kakao.cafe.session.controller;

import com.kakao.cafe.session.exception.NoSuchUserIdException;
import com.kakao.cafe.session.exception.PasswordNotMatchedException;
import com.kakao.cafe.user.model.User;
import com.kakao.cafe.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/session")
public class SessionController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public SessionController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        User user = userService.fetchByUserId(userId)
                .orElseThrow(() -> new NoSuchUserIdException(userId));
        boolean matched = passwordEncoder.matches(password, user.getHashedPassword());
        if (!matched) {
            throw new PasswordNotMatchedException("Given password not matched.");
        }
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
