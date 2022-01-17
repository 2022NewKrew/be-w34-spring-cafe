package com.kakao.cafe.controller.user;

import com.kakao.cafe.controller.user.dto.UserRegisterDto;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegisterController {

    private final UserService userService;

    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public String register(UserRegisterDto userRegisterDto) {
        userService.createUser(
                userRegisterDto.getUserId(),
                userRegisterDto.getPassword(),
                userRegisterDto.getName(),
                userRegisterDto.getEmail()
        );
        return "redirect:/users";
    }
}
