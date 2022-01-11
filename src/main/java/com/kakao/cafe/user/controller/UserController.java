package com.kakao.cafe.user.controller;


import com.kakao.cafe.user.dto.SignUpDTO;
import com.kakao.cafe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/user/create")
    public String singUp(SignUpDTO signUpDTO) {
        userService.signUp(signUpDTO);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String getUserByUserId(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user", userService.findByUserId(userId).orElseThrow(()
                -> new RuntimeException("유저가 존재하지 않습니다."))
        );
        return "user/profile";
    }

}
