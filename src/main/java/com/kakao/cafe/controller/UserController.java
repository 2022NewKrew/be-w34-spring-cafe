package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserFormDTO;
import com.kakao.cafe.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserMapper userMapper;
    private final ModelMapper modelMapper;

    @GetMapping()
    public String userList(Model model) {
        model.addAttribute("users", userMapper.findAll());
        return "/user/list";
    }

    @GetMapping("/login")
    public String userLogin() {
        return "/user/login";
    }

    @GetMapping("/signup")
    public String userSignUpForm() {
        return "/user/form";
    }

    @PostMapping("/signup")
    public String userSignUp(UserFormDTO userFormDTO) {
        User user = modelMapper.map(userFormDTO, User.class);
        userMapper.insert(user);
        return "redirect:/user";
    }

    @GetMapping("/{key}")
    public String userProfile(@PathVariable Long key, Model model) {
        model.addAttribute("user", userMapper.findByKey(key));
        return "/user/profile";
    }
}
