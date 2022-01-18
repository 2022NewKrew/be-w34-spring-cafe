package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.dto.UserLoginRequest;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public String create(UserDto userDto) {
        userService.save(userDto);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String findUserList(Model model) {
        List<User> users = userService.findUserList();
        model.addAttribute("users", users);
        return "user/list";
    }
    @GetMapping("/user/update_failed")

    public String update_fail(Model model) {

        List<User> users = userService.findUserList();
        model.addAttribute("users", users);
        return "user/update_failed_list";
    }
    @PostMapping("/login")
    public String login(UserLoginRequest userLoginRequest, HttpSession session) {
        try {
            User user = userService.validateUserLogin(userLoginRequest);
            session.setAttribute("sessionedUser", user);
        } catch (AuthenticationException e) {
            return "/user/login_failed";
        }
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/users/{userId}")
    public String findUser(@PathVariable("userId") String userId, Model model) {
        User user = userService.findUser(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/users/{id}/form")
    public String createUserUpdateForm(@PathVariable("id") Long id, Model model, HttpSession session) {

        Object value = session.getAttribute("sessionedUser");
        if(value == null)
            return "/user/update_failed";
        try {
            User sessionUser = (User)value;
            userService.validateUserUpdate(sessionUser, id);
            model.addAttribute("user", sessionUser);

        } catch (AuthenticationException e) {
            return "/user/update_failed";
        }
        return "user/updateForm";
    }

    @PutMapping("/users/{id}/update")
    public String updateUser(@PathVariable("id") Long id, UserDto userDto) {
        userService.updateUserInfo(id, userDto);

        return "redirect:/users";
    }
}
