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
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUserList(Model model){
        model.addAttribute("users", userService.findAllUser());
        return "user/list";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute @Validated CreateUserDto createUserDto, Errors errors, Model model){
        if(errors.hasErrors()){
            errors.getFieldErrors().forEach(err -> model.addAttribute(err.getField(), err.getDefaultMessage()));
            return "user/addForm";
        }

        userService.join(createUserDto);
        log.info("Create User - {}", createUserDto);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model){
        ShowUserDto profile = userService.findProfile(userId);
        model.addAttribute("user", profile);
        return "user/profile";
    }

    @GetMapping("/users/{userId}/form")
    public String userUpdateForm(@PathVariable String userId, Model model){
        ShowUserDto profile = userService.findProfile(userId);
        model.addAttribute("user", profile);

        return "user/editForm";
    }

    @PutMapping("/users/{userId}")
    public String userUpdate(@PathVariable String userId, @ModelAttribute @Validated UpdateUserDto updateUserDto, Errors errors, Model model){
        if(errors.hasErrors()){
            errors.getFieldErrors().forEach(err -> model.addAttribute(err.getField(), err.getDefaultMessage()));
            model.addAttribute("user", updateUserDto);
            return "user/editForm";
        }

        User editUser = userService.editProfile(userId, updateUserDto);
        log.info("Update User - {}", editUser);
        return "redirect:/users";
    }
}
