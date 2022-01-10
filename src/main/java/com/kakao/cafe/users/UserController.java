package com.kakao.cafe.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping(path = "/form")
    public String createUserForm() {
        System.out.println("form");
        return "user/form";
    }

    @GetMapping(path = "/list")
    public String userList() {
        System.out.println("list");
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
    public String createUser() {
        System.out.println("hello");
        return "redirect:/user/list";
    }

}
