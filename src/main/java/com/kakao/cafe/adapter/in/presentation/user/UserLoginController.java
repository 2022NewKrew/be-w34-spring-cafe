package com.kakao.cafe.adapter.in.presentation.user;

import com.kakao.cafe.application.user.dto.LoginRequest;
import com.kakao.cafe.application.user.port.in.LoginUserUseCase;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;
import com.kakao.cafe.domain.user.exceptions.WrongPasswordException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

    private static final Logger log = LoggerFactory.getLogger(UserLoginController.class);

    private final LoginUserUseCase loginUserUseCase;

    public UserLoginController(LoginUserUseCase loginUserUseCase) {
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, LoginRequest loginRequest, HttpSession session)
        throws UserNotExistException, WrongPasswordException {
        log.info("[{}]{} tries to login", request.getRequestURI(), loginRequest.getUserId());
        loginUserUseCase.login(loginRequest);
        log.info("[{}]Success user {} login", request.getRequestURI(), loginRequest.getUserId());
        session.setAttribute("sessionedUser", loginRequest.getUserId());
        return "redirect:/";
    }
}
