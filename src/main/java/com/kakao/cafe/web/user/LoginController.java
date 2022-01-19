package com.kakao.cafe.web.user;

import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.service.user.UserLoginService;
import com.kakao.cafe.web.user.dto.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final UserLoginService userLoginService;

    public LoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/user/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {
        logger.info("POST /user/login : {}", email);
        try {
            User user = userLoginService.login(new Email(email), new Password(password));
            session.setAttribute("sessionedUser", new UserResponse(user));
            return "redirect:/";
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.info("로그인 실패");

            redirectAttributes.addFlashAttribute("loginError", e.getMessage());
            session.invalidate();
            return "redirect:/user/login";
        }
    }

    @GetMapping("/logout")
    public String isLogin(HttpSession session) {
        logger.info("GET /user/logout : {}", session.getAttribute("user"));
        session.removeAttribute("sessionedUser");
        return "redirect:/";
    }
}
