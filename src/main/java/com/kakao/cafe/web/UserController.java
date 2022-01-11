package com.kakao.cafe.web;

import com.kakao.cafe.user.UserAPI;
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
    private final UserAPI userAPI = new UserAPI();

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public String getUsers(Model model) {
        List<Users> userList = userAPI.getUserList();
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
        Users user =  userAPI.getByUserId(id);
        model.addAttribute("user", user);
        logger.info("{}", user);
        return "user/profile";
    }

    @PostMapping
    String createUser(Users user) {
        userAPI.createUser(user);
        return "redirect:/users";
    }
}
