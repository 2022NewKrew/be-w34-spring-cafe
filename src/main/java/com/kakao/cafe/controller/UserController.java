package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserList;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public String signUp(User user) {
        userService.save(user);

        logger.info("user create : {}", user.getUserId());
        return "redirect:/user/list";
    }

    @GetMapping("/user/list")
    public String getUserList(Model model) {
        List<User> users = userService.findAll();
        logger.info("user list : {}", users);
        model.addAttribute("users", users);

        return "/user/list";
    }

    @GetMapping("/user/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model) {
        User user = userService.findOneById(userId);

        logger.info("get user profile : {}", user);
        model.addAttribute("user", user);
        return "/user/profile";
    }

    @GetMapping("/user/{userId}/form")
    public String openUserProfile(@PathVariable String userId, Model model, HttpSession session) {
        Object value = session.getAttribute("sessionUser");
        if (value != null) {
            User user = (User) value;
            model.addAttribute("user", user);
        }
        return "/user/updateForm";
    }

    @PostMapping("/user/{userId}/form")
    public String updateUserProfile(@PathVariable String userId, User changedUser) {
        User curUser = UserList.getUserByUserId(userId);
        logger.info("update profile success : {}", changedUser.getName());

        curUser.updateUser(changedUser);

        return "redirect:/user/list";
    }

    @PostMapping("/login")
    public String logIn(String userId, String password, HttpSession session) {
        List<User> user = userService.logIn(userId, password);

        session.setAttribute("sessionUser", user.get(0));
        return "redirect:/";
    }
}
