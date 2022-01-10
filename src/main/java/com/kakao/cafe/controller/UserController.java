package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    UserService userService = new UserService();

    //회원가입 목록 확인
    @RequestMapping(value = "/user/list.html")
    public String userPrintAll(Model model){
        model.addAttribute("users", userService.getAllUser());
        return "/user/list";
    }

    //회원가입요청
    @PostMapping(value = "/user/create")
    public String userCreate(User user, Model model){
        userService.userCreate(user);
        return "redirect:/user/list.html";
    }
}
