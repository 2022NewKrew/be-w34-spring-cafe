package com.kakao.cafe.controller;


import com.kakao.cafe.model.User;
import com.kakao.cafe.service.CafeUserService;
import com.kakao.cafe.service.CafeUserServiceImpl;
import com.kakao.cafe.url.UsersViewURL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class CafeUserController {

    CafeUserService cafeUserService = new CafeUserServiceImpl();

    @PostMapping()
    String signIn(User newUser){ // 회원가입
        cafeUserService.signIn(newUser);
        return UsersViewURL.USER_SIGN_IN.getMappingUrl();
    }

    @GetMapping()
    String getUserList (Model model) { // 유저 목록
        List<User> userList = cafeUserService.getUserList();
        model.addAttribute("userList", userList);
        return UsersViewURL.USER_GET_LIST_VIEW.getMappingUrl();
    }

    @GetMapping("/profile/{userId}")
    String getUserProfile (Model model, @PathVariable("userId") String userId) { // 유저 프로필
        User user = cafeUserService.getUserProfile(userId);
        if( user != null) {
            model.addAttribute("user", user);
        }
        return UsersViewURL.USER_GET_PROFILE_VIEW.getMappingUrl();
    }
}
