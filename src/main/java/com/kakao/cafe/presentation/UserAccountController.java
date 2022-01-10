package com.kakao.cafe.presentation;

import com.kakao.cafe.application.user.UserAccountService;
import com.kakao.cafe.domain.user.UserAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/signup")
    public String signup() {
        return "form";
    }

    @GetMapping("/login")
    public String login() {
        return "loginform";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/list")
    public String userList(Model model) {
        List<UserAccount> users = userAccountService.getAllUser();
        model.addAttribute("users", users);
        model.addAttribute("user-count", users.size());
        return "users";
    }

    @GetMapping("/detail/{id}")
    public String userInfo(@PathVariable(name = "id") Long id, Model model) {
        UserAccount userInfo = userAccountService.getUserInfo(id);
        model.addAttribute("email", userInfo.getEmail());
        model.addAttribute("username", userInfo.getUsername());
        model.addAttribute("createdAt", userInfo.getCreatedAt());
        return "userInfo";
    }
}
