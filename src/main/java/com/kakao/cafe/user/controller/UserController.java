package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.dto.request.SignUpRequest;
import com.kakao.cafe.user.dto.response.UserResponse;
import com.kakao.cafe.user.dto.response.UsersResponse;
import com.kakao.cafe.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String searchUsers(Model model){
        UsersResponse usersResponse = userService.findAll();
        logger.info("회원목록 조회");
        model.addAttribute("users",usersResponse);
        return "user/list";
    }

    @PostMapping("/users")
    public String signUpAccount(SignUpRequest signUpRequest){
        UserResponse userResponse = userService.save(signUpRequest);
        logger.info("회원 가입: {}",userResponse);
        return "redirect:users";
    }
}
