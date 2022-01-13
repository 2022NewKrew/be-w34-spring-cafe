package com.kakao.cafe.web;

import com.kakao.cafe.user.UserService;
import com.kakao.cafe.user.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService = new UserService();

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public String getUsers(Model model) {
        List<Users> userList = userService.getUserList();
        model.addAttribute("userList", userList);
        logger.info("{}", userList);
        return "user/list";
    }
    @GetMapping("/form")
    public String getForm() {
        return "user/form";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Users user =  userService.getByUserId(id);
        model.addAttribute("user", user);
        logger.info("{}", user);
        return "user/profile";
    }

    @PostMapping
    String createUser(Users user) {
        userService.createUser(user);
        return "redirect:/users";
    }
}
