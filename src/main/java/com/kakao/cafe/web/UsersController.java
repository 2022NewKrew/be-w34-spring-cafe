package com.kakao.cafe.web;

import com.kakao.cafe.service.UsersService;
import com.kakao.cafe.web.dto.UserResponseDto;
import com.kakao.cafe.web.dto.UsersCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;


@Controller
public class UsersController {

    private final UsersService usersService = new UsersService();

    @GetMapping("/users")
    public String users(Model model) {
        ArrayList<UserResponseDto> users = usersService.findAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @PostMapping("/users")
    public String Signin(UsersCreateRequestDto requestDto) {
        usersService.save(requestDto);
        return "redirect:/users";
    }

}
