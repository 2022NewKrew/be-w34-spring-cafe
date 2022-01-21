package com.kakao.cafe.controller.authentification;

import com.kakao.cafe.common.authentification.UserIdentification;
import com.kakao.cafe.common.session.SessionKeys;
import com.kakao.cafe.service.authentification.AuthentificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthentificationService authentificationService;

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        UserIdentification userIdentification = authentificationService.login(userId, password);
        session.setAttribute(SessionKeys.USER_IDENTIFICATION, userIdentification);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
