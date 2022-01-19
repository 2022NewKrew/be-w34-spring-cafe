package com.kakao.cafe.controller;

import com.kakao.cafe.controller.auth.AuthControl;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.NoSuchElementException;
import java.util.Objects;

@Controller
public class UserController {
    public static final String TAG_LOGIN_ERROR = "login_error";
    public static final String MSG_LOGIN_FAILED = "아이디 또는 비밀번호가 틀렸습니다.";
    public static final String MSG_REQUIRE_LOGIN = "로그인 후 다시 시도해주세요.";
    public static final String MSG_PW_UPDATED = "수정된 비밀번호로 다시 로그인 해주세요.";
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
    public String getLoginPage(final HttpServletRequest request, final Model model) {
        if (AuthControl.isLogon(request, userService)) {
            return "redirect:/";
        }

        final HttpSession session = request.getSession();
        final String msg = (String)session.getAttribute(TAG_LOGIN_ERROR);

        if (msg != null) {
            session.removeAttribute(TAG_LOGIN_ERROR);
            model.addAttribute("msg", msg);
        }

        return "users/login";
    }

    @PostMapping("/login")
    public String requestLogin(
            final HttpServletRequest request,
            @RequestParam("id") @NonNull final String id,
            @RequestParam("password") @NonNull final String rawPassword
    )
    {
        if (AuthControl.isLogon(request, userService)) {
            return "redirect:/";
        }

        final HttpSession session = request.getSession();
        if (!userService.verifyPassword(id, rawPassword)) {
            logger.info("User login failed - wrong password - " + id);
            return redirectLoginFailed(session);
        }

        AuthControl.login(request, userService.getDto(id));
        logger.info("User login success - " + id);
        return "redirect:/";
    }

    private String redirectLoginFailed(final HttpSession session) {
        session.setAttribute(TAG_LOGIN_ERROR, MSG_LOGIN_FAILED);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String processLogout(final HttpServletRequest request) {
        AuthControl.logout(request);
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
            final UserDto userDto = userService.getDto(id);
            model.addAttribute("user", userDto);
        } catch (NoSuchElementException ignored) {}

        return "users/profile";
    }

    @GetMapping("/users/edit")
    public String getEditPage(final HttpServletRequest request, final Model model) {
        if (!AuthControl.isLogon(request, userService)) {
            return "redirect:/";
        }

        final HttpSession session = request.getSession();
        final UserDto userDto = userService.getDto((String)session.getAttribute(AuthControl.TAG_ID));
        model.addAttribute("email", userDto.getEmail());
        model.addAttribute("idx", userDto.getIdx());

        return "users/edit";
    }

    @PutMapping("/users")
    public String editUser(
            final HttpServletRequest request,
            @NonNull final UserDto userDto,
            @RequestParam("password") @NonNull final String rawPassword,
            @RequestParam("newPassword") @NonNull final String newRawPassword
    )
    {
        if (!AuthControl.isLogon(request, userService)) {
            return "redirect:/";
        }

        if (userService.update(userDto, rawPassword, newRawPassword)) {
            AuthControl.logout(request);
            request.getSession().setAttribute(TAG_LOGIN_ERROR, MSG_PW_UPDATED);
            return "redirect:/login";
        }

        return "redirect:/editUserFailed";
    }

    // Get /editUserFailed -> "users/editUserFailed"
}
