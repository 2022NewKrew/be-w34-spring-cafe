package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.UserCreateRequest;
import com.kakao.cafe.controller.dto.UserUpdateRequest;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.web.meta.SessionConst;
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
        model.addAttribute("users", userService.findAllUsers());
        return "user/list";
    }

    @GetMapping("/{id}")
    public String viewPersonalUser(@PathVariable Long id, Model model, HttpSession session){
        model.addAttribute("user", userService.findOneUser(id));
        return "user/profile";
    }

    @GetMapping("/{id}/form")
    public String viewUpdateForm(@PathVariable Long id, Model model, HttpSession session){
        if((Long)session.getAttribute(SessionConst.LOGIN_USER) != id)
            throw new IllegalStateException("접근할 수 없는 페이지 입니다.");

        model.addAttribute("user", userService.findOneUser(id));
        return "user/update-form";
    }

    @PutMapping("")
    public String updateUser(@ModelAttribute UserUpdateRequest user, HttpSession session){
        userService.updateUser(user, (Long)session.getAttribute(SessionConst.LOGIN_USER));
        return "redirect:/users";
    }


}
