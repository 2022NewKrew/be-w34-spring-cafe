package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserCreateRequest;
import com.kakao.cafe.dto.UserUpdateRequest;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public String signUp(@ModelAttribute UserCreateRequest user){
        userService.signUp(user);
        return "redirect:/users";
    }

    @GetMapping("")
    public String viewUsersList(Model model, HttpSession session){
        if(!isLoginedUser(session)){
            return "user/login";
        }
        model.addAttribute("users", userService.findAllUsers());
        return "user/list";
    }

    @GetMapping("/{id}")
    public String viewPersonalUser(@PathVariable Long id, Model model, HttpSession session){
        if(!isLoginedUser(session)){
            return "user/login";
        }
        model.addAttribute("user", userService.findOneUser(id));
        return "user/profile";
    }

    @GetMapping("/{id}/form")
    public String viewUpdateForm(@PathVariable Long id, Model model, HttpSession session){
        if(!isLoginedUser(session)){
            return "user/login";
        }
        if((Long)session.getAttribute("loginUser") != id)
            throw new IllegalStateException("접근할 수 없는 페이지 입니다.");

        model.addAttribute("user", userService.findOneUser(id));
        return "user/update-form";
    }

    @PutMapping("")
    public String updateUser(@ModelAttribute UserUpdateRequest user, HttpSession session){
        if(!isLoginedUser(session)){
            return "user/login";
        }
        userService.updateUser(user, (Long)session.getAttribute("loginUser"));
        return "redirect:/users";
    }

    private boolean isLoginedUser(HttpSession session){
        if(session.getAttribute("loginUser") != null) return true;
        return false;
    }

}
