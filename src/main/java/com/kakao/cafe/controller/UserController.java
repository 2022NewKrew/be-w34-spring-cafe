package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.util.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/form")
    public String form() {
        return "user/form";
    }

    @GetMapping("/users/form/{userId}")
    public String updateFrom(@PathVariable String userId, Model model, HttpSession httpSession) {
        String currentUser = (String) httpSession.getAttribute("sessionId");
        userService.canUpdate(userId, currentUser);
        User user = userService.findById(userId);
        model.addAttribute("user", UserMapper.toDto(user));
        return "user/updateForm";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute UserDto userDto) {
        userService.insert(UserMapper.toUser(userDto));
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String user(@PathVariable String userId, Model model) {
        User target = userService.findById(userId);
        model.addAttribute("user", UserMapper.toDto(target));
        return "user/userProfile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


    @PostMapping("users/{userId}")
    public String updateUser(@ModelAttribute UserDto userDto, String oldPassword, HttpSession httpSession) {
        userService.canUpdate((String) httpSession.getAttribute("sessionId"), userDto.getUserId());
        User newInfo = UserMapper.toUser(userDto);
        userService.update(newInfo, oldPassword.trim());
        return "redirect:/users";
    }


    @GetMapping("/users")
    public String users(Model model) {
        Users users = userService.findAll();
        List<UserDto> userDtos = users.getUsers().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
        model.addAttribute("users", userDtos);
        return "user/list";
    }

    @GetMapping("/users/deleteAll")
    public String deleteAll() {
        userService.deleteAll();
        return "redirect:/posts";
    }

    @GetMapping("/users/login")
    public String loginPage() {
        return "user/login";
    }

    @PostMapping("/users/login")
    public String login(String id, String password, HttpSession session) {
        LOGGER.info("login() : start");
        session.setAttribute("sessionId", userService.login(id, password));
        LOGGER.info("login() : end");
        return "redirect:/";
    }


}
