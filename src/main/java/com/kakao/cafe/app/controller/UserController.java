package com.kakao.cafe.app.controller;

import com.kakao.cafe.app.request.LoginRequest;
import com.kakao.cafe.app.request.SignUpRequest;
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

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/create")
    public String create(
            // TODO spring-boot-starter-validation 의존성 및 Valid 애노테이션 추가
            @ModelAttribute SignUpRequest request,
            HttpSession session
    ) {
        UserDto user = userService.create(request.toSignUpDto());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user already exists");
        }
        long id = user.getId();
        session.setAttribute("id", id);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String list(Model model) {
        List<UserDto> users = userService.list();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/users/{id}")
    public String profile(@PathVariable("id") String id, Model model, HttpSession session) {
        long parsedId = parseId(session, id);
        UserDto user = userService.get(parsedId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
        model.addAttribute("user", user);
        return "users/item";
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute LoginRequest request, HttpSession session) {
        UserDto user = userService.login(request.toCredentialsDto());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "login failed");
        }
        long id = user.getId();
        session.setAttribute("id", id);
        return "redirect:/users/" + id;
    }

    private long parseId(HttpSession session, String id) {
        if (id.equals("me")) {
            return getId(session);
        }
        return Long.parseLong(id);
    }

    private long getId(HttpSession session) {
        Object attr = session.getAttribute("id");
        if (attr == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "login required");
        }
        return (long) attr;
    }
}
