package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.dao.UserDao;
import com.kakao.cafe.user.dto.UserRegistrationDto;
import com.kakao.cafe.user.exception.NotFoundUserIdException;
import com.kakao.cafe.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    private final UserDao dao;

    @Autowired
    public UserController(UserDao dao) {
        this.dao = dao;
    }

    @PostMapping
    public String register(UserRegistrationDto dto) {
        User user = new User(dto.getUserId(), dto.getPassword(), dto.getName(), dto.getEmail());
        dao.save(user);
        return "redirect:/users";
    }

    @GetMapping
    public String list(Model model) {
        model.addAllAttributes(Map.of("users", dao.fetchAll()));
        model.addAttribute("userCount", dao.getUserCount());
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String profile(@PathVariable String userId, Model model) {
        User fetch = dao.fetchByUserId(userId)
                .orElseThrow(() -> new NotFoundUserIdException(userId));
        model.addAttribute("userId", fetch.getUserId());
        model.addAttribute("name", fetch.getName());
        model.addAttribute("email", fetch.getEmail());
        return "user/profile";
    }
}
