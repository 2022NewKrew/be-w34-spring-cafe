package com.kakao.cafe.controller;

import com.kakao.cafe.CafeApplication;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserId;
import com.kakao.cafe.dto.CreateUserDto;
import com.kakao.cafe.dto.CreateUserRequestDto;
import com.kakao.cafe.dto.FindUserDto;
import com.kakao.cafe.service.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(CafeApplication.class);

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/form")
    public String getSignUp() {
        return "user/form";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute(model.addAttribute("users", users));
        return "user/list";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute CreateUserRequestDto createUserRequestDto) {
        CreateUserDto createUserDto = new CreateUserDto(
            createUserRequestDto.getEmail(),
            createUserRequestDto.getNickname(),
            createUserRequestDto.getPassword()
        );

        UserId createdUserId = userService.join(createUserDto);
        logger.info("[Log] 유저가 생성되었습니다. {}", createdUserId.getUUID());
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String showUser(@PathVariable String userId, Model model) {
        FindUserDto findUserDto = new FindUserDto(new UserId(userId));
        userService.find(findUserDto).ifPresent(user -> model.addAttribute("user", user));
        logger.info("[Log] 유저의 프로필을 조회합니다. {}", userId);
        return "user/profile";
    }
}
