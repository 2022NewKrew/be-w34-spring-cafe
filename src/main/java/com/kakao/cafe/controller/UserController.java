package com.kakao.cafe.controller;

import com.kakao.cafe.controller.auth.AuthControl;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserDto;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.util.SecurePassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;
import java.util.Objects;

@Controller
public class UserController {
    public static final String TAG_LOGIN_ERROR = "login_error";
    public static final String MSG_LOGIN_FAILED = "아이디 또는 비밀번호가 틀렸습니다.";
    public static final String MSG_REQUIRE_LOGIN = "로그인 후 다시 시도해주세요.";
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = Objects.requireNonNull(userService);
    }

    // Get /signup -> "users/signup"

    @PostMapping("/users")
    public String processSignUp(
            @NonNull final UserDto userDto,
            @RequestParam("password") @NonNull final String password
    )
    {
        try {
            userService.add(userDto, password);
        } catch (IllegalStateException e) {
            return "redirect:/dupUserFound";
        }
        logger.info("New User added: " + userDto.getId());
        return "redirect:/users";
    }

    // Get /dupUserFound -> "users/dupUserFound"

    @GetMapping("/login")
    public String getLoginPage(final HttpSession session, Model model) {
        if (AuthControl.isLogon(session, userService)) {
            return "redirect:/";
        }

        String msg = (String)session.getAttribute(TAG_LOGIN_ERROR);

        if (msg != null) {
            session.removeAttribute(TAG_LOGIN_ERROR);
            model.addAttribute("msg", msg);
        }

        return "users/login";
    }

    @PostMapping("/login")
    public String requestLogin(
            final HttpSession session,
            @RequestParam("id") @NonNull final String id,
            @RequestParam("password") @NonNull final String rawPassword
    )
    {
        if (AuthControl.isLogon(session, userService)) {
            return "redirect:/";
        }

        User user;
        try {
            user = userService.getUserEntity(id);
        } catch (NoSuchElementException e) {
            return redirectLoginFailed(session);
        }

        if (!SecurePassword.verify(user.getPassword(), rawPassword)) {
            return redirectLoginFailed(session);
        }

        AuthControl.login(session, user);
        return "redirect:/";
    }

    private String redirectLoginFailed(final HttpSession session) {
        session.setAttribute(TAG_LOGIN_ERROR, MSG_LOGIN_FAILED);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String processLogout(final HttpSession session) {
        AuthControl.logout(session);
        return "redirect:/";
    }

    @GetMapping("/users")
    public String getUserList(final Model model) {
        model.addAttribute("userlist", userService.getList());
        return "users/userlist";
    }

    @GetMapping("/users/{id}")
    public String getUserProfile(
            @PathVariable("id") @NonNull final String id,
            final Model model
    )
    {
        try {
            final UserDto userDto = userService.getUser(id);
            model.addAttribute("user", userDto);
        } catch (NoSuchElementException ignored) {}

        return "users/profile";
    }
}
