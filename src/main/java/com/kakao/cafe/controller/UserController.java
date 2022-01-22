package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.exception.LoginException;
import com.kakao.cafe.exception.UpdateForbiddenException;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.util.Constant;
import com.kakao.cafe.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public String signup(User user) {
        userService.join(user);
        return "redirect:/users";
    }

    @GetMapping
    public String viewUserList(Model model) {
        log.info("viewUserList");
        model.addAttribute("users", userService.findUsers());
        return "users/list";
    }

    @GetMapping("/{userId}/updateForm")
    public String updateForm(@PathVariable Long userId, Model model, HttpSession session) throws AuthenticationException {
        User user = userService.findOne(userId);
        User sessionUser = (User) session.getAttribute(Constant.LOGIN_SESSION);
        if (sessionUser == null) {
            throw new AuthenticationException(ErrorMessage.NO_AUTH.getMsg());
        }

        if (sessionUser.getUserId().equals(userId)) {
            model.addAttribute("userId", userId);
            model.addAttribute("name", user.getName());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("password", user.getPassword());

            return "users/updateForm";
        }
        throw new AuthenticationException(ErrorMessage.USER_PROFILE_UPDATE_FORBIDDEN.getMsg());
    }

    @PutMapping("/{userId}/updateForm")
    public String updateProfile(@PathVariable Long userId, User user, HttpSession session) {
        log.info("update Profile");
        user.setUserId(userId);
        userService.updateUser(user);
        session.setAttribute(Constant.LOGIN_SESSION, user);
        return "redirect:/users";
    }

    @PostMapping("/login_logic")
    public String login(String email, String password, HttpSession session) throws LoginException {
        log.info("login_logic");
        User user = userService.findEmail(email);

        if (user.getPassword().equals(password)) {
            session.setAttribute(Constant.LOGIN_SESSION, user);
            return "redirect:/";
        }
        throw new LoginException(ErrorMessage.LOGIN_FAILED.getMsg());
    }

    @GetMapping("/profile/{userId}")
    public String profile(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.findOne(userId));
        return "users/profile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(Constant.LOGIN_SESSION);
        return "redirect:/";
    }
}
