package com.kakao.cafe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public String processCreationForm(UserCreationForm userCreationForm) {
        userService.registerUser(userCreationForm);
        return "redirect:/users";
    }

    @GetMapping("")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUserView());
        return "user/list";
    }

    @GetMapping("/{username}")
    public String showUser(@PathVariable String username, Model model) {
        model.addAttribute("user", userService.getUserViewByUsername(username));
        return "user/profile";
    }
}
