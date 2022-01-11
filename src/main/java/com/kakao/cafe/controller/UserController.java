package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            @RequestParam("userId") String userId,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            HttpSession session
    ) {
        UserDto user = userService.create(userId, password, name, email);
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
        return "users";
    }

    @GetMapping("/users/{id}")
    public String profile(@PathVariable("id") String id, Model model, HttpSession session) {
        long parsedId = Long.parseLong(id);
        if (id.equals("me")) {
            parsedId = (long) session.getAttribute("id");
        }
        UserDto user = userService.get(parsedId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/user/login")
    public String login(
            @RequestParam("userId") String userId,
            @RequestParam("password") String password,
            HttpSession session
    ) {
        UserDto user = userService.login(userId, password);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "login failed");
        }
        long id = user.getId();
        session.setAttribute("id", id);
        return "redirect:/users/" + id;
    }
}
