package com.kakao.cafe.interfaces.user;

import com.kakao.cafe.application.UserService;
import com.kakao.cafe.interfaces.user.dto.request.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getAllUser() {
        return "member/list";
    }

    @PostMapping("/join")
    public String joinUser(UserDto userDto) {
        userService.join(userDto);
        return "redirect:/user/all";
    }

}