package com.kakao.cafe.user;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.springmvc.HandlebarsView;
import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by melodist
 * Date: 2022-01-10 010
 * Time: 오후 1:25
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String users(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return  "user/list";
    }

    @PostMapping("")
    public String addUser(UserForm userForm) {
        System.out.println("userForm = " + userForm);
        User newUser = new User(userForm.getName(),
                userForm.getPassword(),
                userForm.getName(),
                userForm.getEmail());
        userService.addUser(newUser);

        return "redirect:/user";
    }

    @GetMapping("/{userId}")
    public String userProfile(Model model, @PathVariable("userId") String userId) {
        User user = userService.findUserByUserId(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }
}
