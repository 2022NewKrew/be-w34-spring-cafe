package com.kakao.cafe.web;

import com.kakao.cafe.web.dto.User;
import com.kakao.cafe.web.dto.UsersCreateRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class UsersController {

    private final static ArrayList<User> users = new ArrayList<>();

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", users);
        return "user/list";
    }

    @PostMapping("/users")
    public String Signin(UsersCreateRequestDto requestDto) {
        System.out.println(requestDto.getUserId());
        users.add(new User(requestDto.getUserId(), requestDto.getPassword(), requestDto.getPassword(), requestDto.getEmail()));
        return "redirect:/users";
    }

}
