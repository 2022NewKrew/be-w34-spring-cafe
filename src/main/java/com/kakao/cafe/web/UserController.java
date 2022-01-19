package com.kakao.cafe.web;

import com.kakao.cafe.service.user.UserService;
import com.kakao.cafe.web.dto.user.UserCreateRequestDto;
import com.kakao.cafe.web.dto.user.UserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class UserController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/form.html")
    public String signUpPage() {
        logger.info("signup form");
        return "user/form";
    }

    @PostMapping("user/create")
    public String signUp(UserCreateRequestDto userCreateRequestDto) {
        logger.info("user:{}", userCreateRequestDto);
        userService.userSingUp(userCreateRequestDto);
        return "redirect:/users";
    }

    @GetMapping("users")
    public String viewUserList(Model model) {
        List<UserResponseDto> userList = userService.findAll();
        model.addAttribute("users", userList);
        model.addAttribute("size", userList.size());
        return "/user/list";
    }

    @GetMapping("users/{userId}")
    public String viewUserProfile(@PathVariable String userId, Model model) {
        logger.info("profile:{}", model.addAttribute("userInfo", userService.findById(userId)));
        return "/user/profile";
    }

}
