package com.kakao.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @PostMapping(value = "/user/create")
    public String test(@RequestParam(value = "userId") String userId){
        System.out.println("create id : " + userId);
        return "redirect:/index1.html";
    }
}
