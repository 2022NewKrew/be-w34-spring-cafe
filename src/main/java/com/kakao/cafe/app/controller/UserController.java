package com.kakao.cafe.app.controller;

import com.kakao.cafe.app.request.LoginRequest;
import com.kakao.cafe.app.request.SignUpRequest;
import com.kakao.cafe.service.dto.SessionDto;
import com.kakao.cafe.service.dto.UserDto;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user/create")
    public String create(
            // TODO spring-boot-starter-validation 의존성 및 Valid 애노테이션 추가
            @ModelAttribute SignUpRequest request,
            HttpSession session
    ) {
        UserDto user = service.create(request.toSignUpDto(), session.getId());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user already exists");
        }
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String list(Model model) {
        List<UserDto> users = service.list();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/{id}")
    public String profile(@PathVariable("id") String id, Model model, HttpSession session) {
        UserDto user = service.getById(id, session.getId());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute LoginRequest request, HttpSession session) {
        UserDto user = service.login(request.toCredentialsDto(), session.getId());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "login failed");
        }
        long id = user.getId();
        return "redirect:/users/" + id;
    }
}
