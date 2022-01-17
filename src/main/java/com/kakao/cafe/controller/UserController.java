package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public String create(UserDto userDto) {
        userService.save(userDto);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String findUserList(Model model) {
        List<User> users = userService.findUserList();
        model.addAttribute("users" , users);
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String findUser(@PathVariable("userId") String userId, Model model) {
        User user = userService.findUser(userId);
        model.addAttribute("user" , user);
        return "user/profile";
    }

    @GetMapping("/users/{id}/form")
    public String createUserUpdateForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findUser(id);
        model.addAttribute("user" , user);
        return "user/updateForm";
    }
    @PutMapping("/users/{id}/update")
    public String updateUser(@PathVariable("id") Long id, UserDto userDto) {
        userService.updateUserInfo(id,userDto);

        return "redirect:/users";
    }
}
