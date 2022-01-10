package com.kakao.cafe.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final List<User> users = new ArrayList<>();

    @PostMapping("/users")
    public String save(
            @RequestParam String userId,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam String email
    ) {
        System.out.println("회원가입");
        User newUser = new User(userId, password, name, email);

        users.add(newUser);

        return "redirect: /users";
    }
}
