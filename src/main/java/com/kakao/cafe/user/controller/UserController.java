package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.dto.request.SignUpRequest;
import com.kakao.cafe.user.dto.request.loginRequest;
import com.kakao.cafe.user.dto.response.UserResponse;
import com.kakao.cafe.user.dto.response.UsersResponse;
import com.kakao.cafe.user.service.UserService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final int LOGIN_TIME = 1800;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(HttpSession session, @Valid loginRequest loginRequest) {
        UserResponse userResponse = userService.login(loginRequest);
        session.setMaxInactiveInterval(LOGIN_TIME);
        session.setAttribute("sessionUser", userResponse);

        String destUrl = (String) session.getAttribute("destUrl");
        String redirect = (destUrl == null) ? "/" : destUrl;
        return "redirect:" + redirect;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping
    public String searchUsers(Model model) {
        UsersResponse usersResponse = userService.findAll();
        logger.info("회원목록 조회");
        model.addAttribute("users", usersResponse);
        return "/user/list";
    }

    @PostMapping
    public String signUpAccount(@Valid SignUpRequest signUpRequest) {
        UserResponse userResponse = userService.save(signUpRequest);
        logger.info("회원 가입: {}", userResponse);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String searchUser(Model model, @PathVariable Long id) {
        UserResponse userResponse = userService.findById(id);
        logger.info("회원 조회: {}", userResponse);
        model.addAttribute("user", userResponse);
        return "/user/profile";
    }
}
