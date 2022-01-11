package com.kakao.cafe.controller;


import com.kakao.cafe.helper.CollectionHelper;
import com.kakao.cafe.model.User;
import com.kakao.cafe.service.CafeUserService;
import com.kakao.cafe.service.CafeUserServiceImpl;
import com.kakao.cafe.url.UserRedirect;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(UserRedirect.USER_BASE_URL)
public class CafeUserController {

    CafeUserService cafeUserService;

    public CafeUserController () {
        cafeUserService = new CafeUserServiceImpl();
    }

    @GetMapping("/sign-in")
    String userViewSignIn() {
        return "/user/login";
    }
    @GetMapping("/sign-up")
    String userViewSingUp() {
        return "/user/form";
    }

    @PostMapping("/sign-in")
    String signIn(User newUser){ // 회원가입
        cafeUserService.signIn(newUser);
        return "redirect:/users/list";
    }

    @GetMapping("/list")
    String getUserList (Model model) { // 유저 목록
        List<User> userList = cafeUserService.getUserList();
        model.addAttribute("userList", userList);
        model.addAttribute("userCnt", CollectionHelper.getItemNumberOfList(userList));
        return "/user/list";
    }

    @GetMapping("/profile/{userId}")
    String getUserProfile (Model model, @PathVariable("userId") String userId) { // 유저 프로필
        User user = cafeUserService.getUserProfile(userId);
        if( user != null) {
            model.addAttribute("user", user);
        }
        return "/user/profile";
    }
}
