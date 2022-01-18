package com.kakao.cafe.controller;

import com.kakao.cafe.domain.dto.UserLoginDTO;
import com.kakao.cafe.domain.dto.UserSignUpDTO;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.UsesSunMisc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/signUp")
    public String signUpView(){
        return "user/signUp";
    }

    @PostMapping("/signUp")
    public String signUpUser(@Valid UserSignUpDTO userSignUpDTO){
        userService.signUp(userSignUpDTO);
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String userList(Model model){
        model.addAttribute("users",userService.findAllUsers());
        return "user/list";
    }

    @GetMapping("/profile/{userId}")
    public String userProfile(Model model, @PathVariable String userId){
        model.addAttribute("user", userService.findUserById(userId));
        return "user/profile";
    }

    @GetMapping("/login")
    public String loginView(HttpSession session){
        if(session.getAttribute("sessionedUser")!=null) return "redirect:/";
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO, HttpSession session) throws Exception {
        session.setAttribute("sessionedUser", userService.login(userLoginDTO));
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
