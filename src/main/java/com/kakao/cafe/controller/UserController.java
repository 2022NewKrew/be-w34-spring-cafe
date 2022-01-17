package com.kakao.cafe.controller;


import com.kakao.cafe.dto.UserCreateDto;
import com.kakao.cafe.dto.UserShowDto;

import com.kakao.cafe.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;


@Slf4j
@Controller
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/create")
    public String addUser(@ModelAttribute @Validated UserCreateDto userCreateDto
            , BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult
                    .getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList()));
            return "user/form_failed";
        }
        userService.save(userCreateDto);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable Integer id, Model model) {
        UserShowDto user = userService.findOne(id);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/users/{id}/form")
    public String getUpdateForm(@PathVariable Integer id, Model model) {
        UserShowDto user = userService.findOne(id);
        model.addAttribute("user", user);
        return "user/updateForm";
    }

    @PostMapping("/users/{id}/update")
    public String updateUser(@PathVariable Integer id, UserShowDto user) {
        userService.findAll().set(id, user);
        return "redirect:/users";
    }

    @GetMapping("/login/form")
    public String loginForm(){
        return "user/login";
    }
}
