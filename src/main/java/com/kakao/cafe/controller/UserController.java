package com.kakao.cafe.controller;


import com.kakao.cafe.dto.SignUpDTO;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;



    @PostMapping("/user/create")
    public String singUp(SignUpDTO signUpDTO){
        userService.signUp(signUpDTO);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("users",userService.getAllUsers());
        return "user/list";
    }

}
