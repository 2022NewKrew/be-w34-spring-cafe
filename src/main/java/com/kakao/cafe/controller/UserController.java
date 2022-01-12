package com.kakao.cafe.controller;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userDao.getUsers();
        model.addAttribute("users", users);
        return "/user/list";
    }

    @PostMapping("/user/create")
    public String signUp(User user) {
        userDao.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String getProfile(@PathVariable String userId, Model model) {
        User user = userDao.getUser(userId);
        model.addAttribute("user", user);
        return "/user/profile";
    }

    @GetMapping("/users/{userId}/form")
    public String updateForm(@PathVariable String userId, Model model) {
        User user = userDao.getUser(userId);
        model.addAttribute("userId", userId);
        return "/user/updateForm";
    }

    @PostMapping("/user/edit")
    public String editUser(User user) {
        userDao.updateUser(user);
        return "redirect:/users";
    }

}
