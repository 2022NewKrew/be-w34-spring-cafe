package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @GetMapping("/users")
    public String userPage(){
        return "/user/list.html";
    }

    @PostMapping("/users")
    public String userPost(User user){
        System.out.println(user.getUserId());
        System.out.println(user.getEmail());
        System.out.println("들어옴ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
        return "redirect:/users";
    }
}
