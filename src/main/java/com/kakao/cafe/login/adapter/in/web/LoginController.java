package com.kakao.cafe.login.adapter.in.web;

import com.kakao.cafe.common.meta.SessionData;
import com.kakao.cafe.common.meta.URLPath;
import com.kakao.cafe.login.application.port.in.LoginForm;
import com.kakao.cafe.login.application.port.in.LoginUseCase;
import com.kakao.cafe.login.application.port.in.UserLoginResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginUseCase loginUseCase;

    @GetMapping("user/login")
    public String loginForm() {
        return URLPath.LOGIN_FORM.getPath();
    }

    @PostMapping("user/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm,
                        HttpSession session,
                        BindingResult bindingResult) throws LoginException {
        final boolean hasNotUserKey = session.getAttribute(SessionData.USER_KEY) == null;

        if (bindingResult.hasErrors()) {
            return URLPath.LOGIN_FORM.getRedirectPath();
        }
        if (hasNotUserKey) {
            final UserLoginResult userLoginResult = loginUseCase.login(loginForm);
            session.setAttribute(SessionData.USER_KEY, userLoginResult.getId());
            session.setAttribute(SessionData.USER_NAME, userLoginResult.getNickname());
        }
        return URLPath.INDEX.getRedirectPath();
    }

    @GetMapping("user/login_failed")
    public String loginFail() {
        return URLPath.LOGIN_FAILED.getPath();
    }

    @GetMapping("/users/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return URLPath.INDEX.getRedirectPath();
    }

}
