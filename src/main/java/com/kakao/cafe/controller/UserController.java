package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import com.kakao.cafe.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Resource(name = "userService")
    UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    String getUsers(Model model) {
        List<User> userList = userService.getUserList();
        model.addAttribute("users", userList);

        return "user/list";
    }

    @PostMapping("/create")
    String createUser(User user) {
        userService.insertUser(user);
        logger.info("create User -> UserId : {}, Email : {}", user.getUserId(), user.getEmail());

        return "redirect:/users";
    }

    @GetMapping("/{id}/form")
    String getUserForm(@PathVariable long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        logger.info("get User(Form) -> ID : {}, UserId : {}", user.getId(), user.getUserId());

        return "user/updateForm";
    }

    @PostMapping("/{userId}/update")
    String updateUser(User user) {
        userService.updateUser(user);
        logger.info("update User -> UserId : {}, Email : {}", user.getUserId(), user.getEmail());
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    String getUserProfile(@PathVariable long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        logger.info("get User(Profile) -> ID : {}, UserId : {}", user.getId(), user.getUserId());

        return "user/profile";
    }

}
