package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserProfileResponse;
import com.kakao.cafe.dto.UserSignUpRequest;
import com.kakao.cafe.dto.UserUpdateRequest;
import com.kakao.cafe.interceptor.AuthenticationSecured;
import com.kakao.cafe.interceptor.PersonalAuthorizationSecured;
import com.kakao.cafe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String list(Model model) {
        log.info("start list()");
        model.addAttribute("users", userService.list());
        return "/users/list";
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String signUp(UserSignUpRequest request) {
        log.info("start signUp()");
        userService.signUp(request);
        return "redirect:/users";
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String signUpJson(@RequestBody UserSignUpRequest request) {
        log.info("start signUp()");
        userService.signUp(request);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String profile(@PathVariable int id, Model model) {
        log.info("start profile()");
        User user = userService.findUser(id);
        model.addAttribute("userProfile", UserProfileResponse.from(user));
        return "/users/profile";
    }

    @GetMapping("/users/{id}/form")
    @AuthenticationSecured
    @PersonalAuthorizationSecured
    public String getUpdateForm(@PathVariable int id, Model model) {
        log.info("start getUpdateForm()");
        User user = userService.findUser(id);
        model.addAttribute("userProfile", UserProfileResponse.from(user));
        return "/users/update-form";
    }

    @PutMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @AuthenticationSecured
    @PersonalAuthorizationSecured
    public String update(@PathVariable int id, UserUpdateRequest request, HttpSession session, Model model) {
        log.info("start update()");
        userService.update(id, request);
        return "redirect:/users";
    }

    @GetMapping("/users/login")
    public String loginForm() {
        log.info("start loginForm()");
        return "/users/login";
    }

    @PostMapping("/users/login")
    public String login(String userId, String password, HttpSession session, Model model) {
        log.info("start login()");
        User user = userService.login(userId, password);
        if (user != null) {
            session.setAttribute("sessionedUser", user);
            return "redirect:/";
        }

        model.addAttribute("isFailed", true);
        return "/users/login";
    }

    @GetMapping("/users/logout")
    public String logout(HttpSession session) {
        if (session.getAttribute("sessionedUser") != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentException(IllegalArgumentException e, HttpServletRequest request, Model model) {
        log.info("start illegalArgumentException()");
        model.addAttribute("referer", request.getHeader("referer"));
        model.addAttribute("message", e.getMessage());
        return "/error";
    }

}
