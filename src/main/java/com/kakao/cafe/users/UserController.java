package com.kakao.cafe.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model) {
        List<UserDto> userDtoList = userService.getUsers();

        model.addAttribute("users", userDtoList);

        return "user/list";
    }

    @PostMapping
    public String signUpUser(SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);

        return "redirect:/users";
    }
}
