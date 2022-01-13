package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.UserUpdateReqDto;
import com.kakao.cafe.service.user.UserService;

import com.kakao.cafe.dto.user.UserReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getUserList(Model model){
        model.addAttribute("users", userService.findUsers());
        return "user/list";
    }

    @PostMapping
    public String createUser(@ModelAttribute UserReqDto userReqDto){
        userService.addUser(userReqDto);
        return "redirect:/users";
    }

    @GetMapping("/form")
    public String getForm(){
        return "user/form";
    }

    @GetMapping("/{id}")
    public String getId(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.findUserById(id));
        return "user/profile";
    }

    @GetMapping("/{id}/form")
    public String getUpdateForm(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.findUserById(id));
        return "user/updateForm";
    }

    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserUpdateReqDto userUpdateReqDto){
        userUpdateReqDto.setId(id);
        userService.updateUser(userUpdateReqDto);
        return "redirect:/users";
    }


}
