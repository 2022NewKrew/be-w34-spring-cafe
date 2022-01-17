package com.kakao.cafe.module.controller;

import com.kakao.cafe.module.model.dto.UserDtos;
import com.kakao.cafe.module.service.InfraService;
import com.kakao.cafe.module.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final InfraService infraService;

    @PostMapping
    public String signUp(UserDtos.UserSignUpDto userSignUpDto) {
        userService.signUp(userSignUpDto);
        logger.info("Create User : {}", userSignUpDto.getName());
        return "redirect:/users";
    }

    @PostMapping("/sign-in")
    public String signIn(UserDtos.UserSignInDto userSignInDto, HttpSession session) {
        UserDtos.UserDto userDto = userService.signIn(userSignInDto);
        session.setAttribute("sessionUser", userDto);
        logger.info("Sign in User : {}", userDto.getName());
        return "redirect:/";
    }

    @GetMapping("/sign-out")
    public String signOut(HttpSession session) throws HttpSessionRequiredException {
        UserDtos.UserDto userDto = infraService.retrieveUserSession(session);
        session.invalidate();
        logger.info("Sign out User : {}", userDto.getName());
        return "redirect:/";
    }
}
