package com.kakao.cafe.controller;


import com.kakao.cafe.dto.UserCreateDto;
import com.kakao.cafe.dto.UserLoginDto;
import com.kakao.cafe.dto.UserShowDto;

import com.kakao.cafe.dto.UserUpdateDto;
import com.kakao.cafe.model.User;
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

import javax.servlet.http.HttpSession;
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
        return "redirect:/login/form";
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

    @GetMapping("/login/form")
    public String loginForm(){
        return "user/login";
    }

    @PostMapping("/login")
    public String login(UserLoginDto userLoginDto, HttpSession session, Model model) {
        logger.info("id = {}, pw = {}", userLoginDto.getUserId(), userLoginDto.getPassword());
        try {
            User user = userService.validate(userLoginDto);
            session.setAttribute("sessionUser", user);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "/user/login_failed";
        }
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/update")
    public String getUpdateForm(HttpSession session, Model model) {
        Object value = session.getAttribute("sessionUser");
        if (value != null) {
            User sessionUser = (User)value;
            model.addAttribute("user", sessionUser);
            return "user/updateForm";
        }
        return "redirect:/login";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Integer id, UserUpdateDto userUpdateDto){
        userService.update(id, userUpdateDto);
        return "redirect:/";
    }

}
