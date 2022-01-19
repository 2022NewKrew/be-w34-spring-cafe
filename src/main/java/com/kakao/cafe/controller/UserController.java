package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserCreateRequest;
import com.kakao.cafe.dto.UserLoginRequest;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String findUserList(Model model) {
        List<User> users = userService.findUserList();
        model.addAttribute("users", users);
        return "/user/list";
    }
    @GetMapping("/{userId}")
    public String findUser(@PathVariable("userId") String userId, Model model) {
        User user = userService.findUser(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }
    @PostMapping("/signup")
    public String signup(UserCreateRequest userCreateRequest) {
        userService.save(userCreateRequest);
        return "redirect:/users";
    }
    @PostMapping("/login")
    public RedirectView login(UserLoginRequest userLoginRequest, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.validateUserLogin(userLoginRequest);
            session.setAttribute("sessionedUser", user);
            return new RedirectView("/");
        } catch (AuthenticationException e) {
            redirectAttributes.addFlashAttribute("flashMessage", "로그인에 실패했습니다");
            return new RedirectView("/login");
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @GetMapping("/{id}/update")
    public RedirectView createUserUpdateForm(@PathVariable("id") Long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        Object value = session.getAttribute("sessionedUser");
        try {
            User sessionUser = (User)value;
            userService.validateUserUpdate(sessionUser, id);
            redirectAttributes.addFlashAttribute("user", sessionUser);
            return new RedirectView("/update");
        } catch (AuthenticationException e) {
            redirectAttributes.addFlashAttribute("flashMessage", "자기 정보만 수정할 수 있습니다.");
            return new RedirectView("/users");
        }
    }

    @PutMapping("/{id}/update")
    public RedirectView updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user, UserCreateRequest userUpdateRequest, RedirectAttributes redirectAttributes) {
        try {
            userService.updateUserInfo(id, userUpdateRequest);
            return new RedirectView("/users");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("flashMessage", "비밀번호가 일치하지 않습니다.");
            redirectAttributes.addFlashAttribute("user", user);
            return new RedirectView("/update");
        }
    }
}
