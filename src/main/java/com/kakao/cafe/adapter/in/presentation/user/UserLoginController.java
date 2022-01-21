package com.kakao.cafe.adapter.in.presentation.user;

import com.kakao.cafe.application.user.dto.LoginRequest;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.application.user.port.in.LoginUserUseCase;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;
import com.kakao.cafe.domain.user.exceptions.WrongPasswordException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

    private final LoginUserUseCase loginUserUseCase;

    public UserLoginController(LoginUserUseCase loginUserUseCase) {
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/user/login")
    public String login(LoginRequest loginRequest, HttpSession session)
        throws UserNotExistException, WrongPasswordException {
        UserInfo userInfo = loginUserUseCase.login(loginRequest);
        session.setAttribute("sessionedUser", userInfo);
        return "redirect:/";
    }
}
