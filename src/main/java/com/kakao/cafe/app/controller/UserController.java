package com.kakao.cafe.app.controller;

import com.kakao.cafe.app.request.LoginRequest;
import com.kakao.cafe.app.request.SignUpRequest;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/create")
    public String create(
            @Valid @ModelAttribute SignUpRequest request,
            HttpSession session
    ) {
        UserDto user = userService.create(request.toSignUpDto());
        long id = user.getId();
        session.setAttribute("currentUserId", id);
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
        Optional<UserDto> user = userService.get(parsedId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
        model.addAttribute("user", user.get());
        return "users/item";
    }

    @PostMapping("/user/login")
    public String login(
            @Valid @ModelAttribute LoginRequest request,
            HttpSession session
    ) {
        UserDto user = userService.login(request.toCredentialsDto());
        long id = user.getId();
        session.setAttribute("currentUserId", id);
        return "redirect:/users/" + id;
    }

    private long parseId(HttpSession session, String id) {
        if (id.equals("me")) {
            return getId(session);
        }
        return Long.parseLong(id);
    }

    private long getId(HttpSession session) {
        Object attr = session.getAttribute("currentUserId");
        if (attr == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "login required");
        }
        return (long) attr;
    }
}
