package com.kakao.cafe.controller;

import com.kakao.cafe.user.User;
import com.kakao.cafe.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    public String insertUser(User user) {

        Long id = userService.save(user);

        return "redirect:/";
    }

    @GetMapping
    public String viewUserList(Model model) {
        List<User> users = userService.findAll();

        model.addAttribute("users", users);

        return "user/list";
    }

    @GetMapping("/create")
    public String viewQuestionForm() {
        return "user/form";
    }

}
