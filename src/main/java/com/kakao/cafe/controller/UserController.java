package com.kakao.cafe.controller;

import com.kakao.cafe.exception.DuplicateUserException;
import com.kakao.cafe.exception.NotLoginException;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    public static final String SESSIONED_USER = "sessionedUser";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "/user/list";
    }

    @PostMapping("/user/create")
    public String signUp(User user) throws DuplicateUserException {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String getProfile(@PathVariable String userId, Model model) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "/user/profile";
    }

    @GetMapping("/users/login/profile")
    public String getLoginProfile(Model model, HttpSession session) throws NotLoginException {
        User loginUser = getLoginUser(session);
        userService.checkLogin(loginUser);
        model.addAttribute("user", loginUser);
        return "/user/profile";
    }

    @GetMapping("/users/{userId}/form")
    public String updateForm(@PathVariable String userId, Model model) {
        model.addAttribute("userId", userId);
        return "/user/updateForm";
    }

    @GetMapping("/users/edit/profile")
    public String editProfile(Model model, HttpSession session) {
        User loginUser = getLoginUser(session);
        String userId = loginUser.getUserId();
        model.addAttribute("userId", userId);
        return "/user/updateForm";
    }

    @PostMapping("/user/edit")
    public String editUser(User user, HttpSession session) throws Exception {
        User loginUser = getLoginUser(session);
        userService.updateUser(user, loginUser);
        return "redirect:/users";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) throws Exception {
        User user = userService.login(userId, password);
        session.setAttribute(SESSIONED_USER, user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    public User getLoginUser(HttpSession session) {
        Object loginUserObject = session.getAttribute(SESSIONED_USER);
        if(loginUserObject == null) {
            return null;
        }
        return (User)loginUserObject;
    }

}
