package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model) {
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList", userList);
        return "/user/list";
    }

    @PostMapping
    public String signUp(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    public String userInfo(@PathVariable String userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/signup")
    public String singUpPage() {
        return "user/form";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session) {
        //유효한 user인지 받고
        //session에 등록
        session.setAttribute("curUser", user);
        System.out.println("세션 출력");
        System.out.println(session.getAttribute("curUser"));
        return "redirect:/";
    }

}
