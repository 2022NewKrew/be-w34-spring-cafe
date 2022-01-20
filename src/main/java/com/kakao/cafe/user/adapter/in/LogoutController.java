package com.kakao.cafe.user.adapter.in;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("sessionedUser");
        return "redirect:/";
    }
}
