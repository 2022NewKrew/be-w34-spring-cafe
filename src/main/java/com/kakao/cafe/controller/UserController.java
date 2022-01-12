package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.user.CreateUserDto;
import com.kakao.cafe.dto.user.ShowUserDto;
import com.kakao.cafe.dto.user.UpdateUserDto;
import com.kakao.cafe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUserList(Model model){
        model.addAttribute("users", userService.findAllUser());
        return "user/list";
    }

    @GetMapping("/user/form")
    public String userForm(){
        return "user/addForm";
    }

    @PostMapping("/user")
    public String createUser(@ModelAttribute CreateUserDto createUserDto){
        User user = userService.join(createUserDto);
        log.info("Create User - {}", user);
        return "redirect:/user";
    }

    @GetMapping("/user/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model){
        ShowUserDto profile = userService.findProfile(userId);
        model.addAttribute("user", profile);
        return "user/profile";
    }

    @GetMapping("/user/{userId}/form")
    public String userUpdateForm(@PathVariable String userId, Model model){
        ShowUserDto profile = userService.findProfile(userId);
        model.addAttribute("user", profile);

        return "user/editForm";
    }

    @PostMapping("/user/{userId}/update")
    public String userUpdate(@PathVariable String userId, @ModelAttribute UpdateUserDto updateUserDto){
        User editUser = userService.editProfile(userId, updateUserDto);
        log.info("Update User - {}", editUser);
        return "redirect:/user";
    }
}
