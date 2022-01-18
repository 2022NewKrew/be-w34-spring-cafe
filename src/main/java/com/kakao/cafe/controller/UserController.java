package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.ProfileDto;
import com.kakao.cafe.dto.user.SimpleUserInfo;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    public ModelAndView userProfile(@PathVariable("userId") Long userId, ModelAndView mav) {
        ProfileDto profileDto = userService.findProfileById(userId);
        mav.addObject("profile", profileDto);

        mav.setViewName("userProfile");

        return mav;
    }

    @GetMapping("/users/join/success/{userId}")
    public ModelAndView joinSuccessView(@PathVariable("userId") Long userId, ModelAndView mav) {
        SimpleUserInfo simpleUserInfo = userService.findSimpleUserInfoById(userId);
        mav.addObject("simpleUserInfo", simpleUserInfo);

        mav.setViewName("join_success");

        return mav;
    }
}
