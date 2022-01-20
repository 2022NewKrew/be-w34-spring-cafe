package com.kakao.cafe.web;

import com.kakao.cafe.user.UserService;
import com.kakao.cafe.user.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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

    @GetMapping("/login")
    public String getLogin() {
        return "user/login";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Users user =  userService.getUserById(id);
        model.addAttribute("user", user);
        logger.info("{}", user);
        return "user/profile";
    }

    @PostMapping
    String createUser(Users user) {
        userService.createUser(user);
        return "redirect:/users";
    }

    @PostMapping("/login")
    String login(String userId, String password, HttpSession session) {
        Users user = userService.getUserByUserId(userId);
        if (user.getPassword().equals(password)) {
            session.setAttribute("sessionedUser", user);
            return "redirect:/users";
        }
        return "user/login";
    }
}
