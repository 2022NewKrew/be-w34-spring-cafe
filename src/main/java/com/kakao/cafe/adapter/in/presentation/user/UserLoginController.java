package com.kakao.cafe.adapter.in.presentation.user;

import com.kakao.cafe.application.user.dto.LoginRequest;
import com.kakao.cafe.application.user.port.in.LoginUserUseCase;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;
import com.kakao.cafe.domain.user.exceptions.WrongPasswordException;
import com.kakao.cafe.view.ErrorMessage;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserLoginController {

    private static final Logger log = LoggerFactory.getLogger(UserLoginController.class);

    private final LoginUserUseCase loginUserUseCase;

    public UserLoginController(LoginUserUseCase loginUserUseCase) {
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/login")
    public String login(LoginRequest loginRequest, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            log.info("{} tries to login", loginRequest.getUserId());
            loginUserUseCase.login(loginRequest);
            log.info("{} login success", loginRequest.getUserId());
            session.setAttribute("sessionedUser", loginRequest.getUserId());
            return "redirect:/";
        } catch (UserNotExistException | WrongPasswordException e) {
            log.info("login failed");
            log.info("{}", e.getMessage());
            redirectAttributes.addAttribute("message", ErrorMessage.getErrorMessage(e));
            return "redirect:/login_fail";
        }
    }

    @GetMapping("/login_fail")
    public String loginFailed(@RequestParam String message, Model model) {
        model.addAttribute("message", message);
        return "user/login_failed";
    }
}
