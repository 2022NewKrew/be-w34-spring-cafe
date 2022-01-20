package com.kakao.cafe.controller;

import com.kakao.cafe.exception.user.NotAllowedUserException;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.util.annotation.Auth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping()
    public String userListView(Model model) {
        List<UserDto> users = userService.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String profileView(@PathVariable String userId, Model model) {
        UserDto user = userService.filterUserById(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/signup")
    public String signupView() {
        return "user/form";
    }

    @PostMapping("/signup")
    public String signup(UserDto user) {
        userService.signupUser(user);
        return "redirect:";
    }

    @Auth
    @GetMapping("/{userId}/update")
    public String updateView(@PathVariable String userId, HttpSession session, Model model) {
        UserDto loginUser = (UserDto) session.getAttribute("sessionedUser");
        if (loginUser.getUserId().equals(userId)) {
            UserDto user = userService.filterUserById(userId);
            model.addAttribute("user", user);
            return "user/updateForm";
        }
        throw new NotAllowedUserException();
    }

    @Auth
    @PutMapping("/{userId}/update")
    public String update(UserDto user, String newPassword) {
        userService.updateUser(user, newPassword);
        return "redirect:";
    }

    @GetMapping("/login")
    public String loginView() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        UserDto user = userService.loginUser(userId, password);
        session.setAttribute("sessionedUser", user);
        return "redirect:/";
    }

    @Auth
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
