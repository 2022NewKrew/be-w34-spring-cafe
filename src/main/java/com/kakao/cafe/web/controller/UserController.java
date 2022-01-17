package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.UserCreateDTO;
import com.kakao.cafe.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private final UserService userService;
    private final Logger logger;

    UserController(UserService userService) {
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @GetMapping("/users")
    public String getUserList(Model model) {
        model.addAttribute("userList", userService.getUserList());
        return "/user/list";
    }

    @GetMapping("/users/form")
    public String getCreateUserForm() {
        return "/user/form";
    }

    @PostMapping("/users/create")
    public String createUser(String userId, String password, String name, String email) {
        UserCreateDTO userCreateDTO = new UserCreateDTO(userId, password, name, email);
        userService.signUp(userCreateDTO);
        return "redirect:/users";
    }


    @GetMapping("/users/{userId}")
    public String getUserProfile(Model model, @PathVariable String userId) {
        User user = userService.getUserByUserId(userId);
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());
        return "/user/profile";
    }

    @GetMapping("/users/{userId}/form")
    public String getUserUpdateForm(Model model, @PathVariable String userId) {
        User user = userService.getUserByUserId(userId);
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());
        return "/user/update-form";
    }

    @PostMapping("/users/{userId}/update")
    public String updateUser(HttpSession session, @PathVariable String userId, String password, String passwordConfirm, String name, String email) {
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            return "redirect:/users";
        }
        User user = (User)value;
        if (user.getPassword().equals(password) && password.equals(passwordConfirm)) {
            UserCreateDTO userUpdateDTO = new UserCreateDTO(userId, password, name, email);
            userService.updateUser(userUpdateDTO);
        }
        return "redirect:/users";
    }



    @GetMapping("/users/login")
    public String getLoginForm() { return "/user/login"; }

    @PostMapping("/users/login")
    public String login(String userId, String password, HttpSession session) {
        User user = userService.getUserByUserId(userId);
        if (user.getPassword().equals(password)) {
            session.setAttribute("sessionedUser", user);
        }
        return "redirect:/";
    }
}
