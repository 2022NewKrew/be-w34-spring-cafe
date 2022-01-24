package com.kakao.cafe.user.controller;

import com.kakao.cafe.common.auth.LoginUser;
import com.kakao.cafe.common.util.SessionUtil;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.LoginRequest;
import com.kakao.cafe.user.dto.SessionUser;
import com.kakao.cafe.user.dto.SignupRequest;
import com.kakao.cafe.user.service.UserService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SessionUtil sessionUtil;

    @PostMapping
    public String signup(@Valid SignupRequest request) {
        User user = request.toEntity();
        userService.signup(user);
        return "redirect:/user";
    }

    @GetMapping
    public String getAllUsers(@LoginUser SessionUser user, Model model) {
        model.addAttribute("users", userService.getProfiles());
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String getUserById(@LoginUser SessionUser user, @PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.getProfileById(userId));
        return "user/profile";
    }

    @PostMapping("/login")
    public String login(LoginRequest request, HttpSession session) {
        sessionUtil.add(userService.login(request), session);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(@LoginUser SessionUser user, HttpSession session) {
        sessionUtil.remove(session);
        return "redirect:/";
    }
}
