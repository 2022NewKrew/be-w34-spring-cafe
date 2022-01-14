package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.dto.UserForm;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("")
    public String create(@Valid UserForm form) {
        logger.info("POST /users");
        User newUser = User.of(form);
        userService.join(newUser);
        return "redirect:/users";
    }

    // 유저 목록
    @GetMapping("")
    public String list(Model model) {
        logger.info("GET /users");
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    // 유저 프로필
    @GetMapping("/{userId}")
    public String profile(@PathVariable String userId, Model model){
        logger.info("GET /users/{userId}");
        User user = userService.findByUserId(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/{userId}/form")
    public String updateForm(@PathVariable String userId, Model model){
        User user = userService.findByUserId(userId);
        model.addAttribute("user", user);
        return "user/updateForm";
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable String userId, UserForm userForm){
        logger.info("PUT /{userId}/update");
        userForm.setUserId(userId);
        userService.updateUser(User.of(userForm));
        return "redirect:/users";
    }

}
