package com.kakao.cafe.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/form")
    public String createUserForm() {
        return "user/form";
    }

    @GetMapping(path = "/list")
    public String userList(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping(path = "/login")
    public String userLogin() {
        System.out.println("login");
        return "user/login";
    }

    @GetMapping(path = "/login_failed")
    public String userLoginFailed() {
        System.out.println("login_failed");
        return "user/login_failed";
    }

    @GetMapping(path = "/profile")
    public String userProfile() {
        System.out.println("profile");
        return "user/profile";
    }

    @PostMapping(path = "/create")
    public String createUser(UserRequest userRequest, Model model) {
        userService.createUser(new User(userRequest));
        return "redirect:/user/list";
    }

}
