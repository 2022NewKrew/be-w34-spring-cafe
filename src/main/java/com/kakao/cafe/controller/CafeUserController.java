package com.kakao.cafe.controller;


import com.kakao.cafe.helper.CollectionHelper;
import com.kakao.cafe.model.User;
import com.kakao.cafe.service.CafeUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/users")
public class CafeUserController {
    final CafeUserService cafeUserService;
    public CafeUserController (CafeUserService cafeUserService) {
        this.cafeUserService = cafeUserService;
    }

    private static final String USER_DIRECTORY = "user";
    public static final String USER_VIEW_SIGN_UP = USER_DIRECTORY+"/form";
    public static final String USER_VIEW_SIGN_IN = USER_DIRECTORY+"/login";
    public static final String USER_VIEW_LIST = USER_DIRECTORY+"/list";
    public static final String USER_VIEW_PROFILE = USER_DIRECTORY+"/profile";

    public static final String REDIRECT_PREFIX = "redirect:";
    public static final String USER_REDIRECT_SIGN_IN_SUCCESS = REDIRECT_PREFIX+"/";
    public static final String USER_REDIRECT_SIGN_IN_FAIL = REDIRECT_PREFIX+"/users/sign-in/fail";
    public static final String USER_REDIRECT_LIST = REDIRECT_PREFIX+"/users/list";
    public static final String USER_REDIRECT_SIGN_UP_FAIL = REDIRECT_PREFIX+"/users/sign-up/fail";

    @GetMapping("/sign-in")
    String userViewSignIn() {
        return USER_VIEW_SIGN_IN;
    }
    @PostMapping("/sign-in")
    String signIn(HttpSession httpSession, User signInUser) {
        if(cafeUserService.SignIn(signInUser)) {
            httpSession.setAttribute("signInUser", signInUser);
            return USER_REDIRECT_SIGN_IN_SUCCESS;
        }
        return USER_REDIRECT_SIGN_IN_FAIL;
    }

    @GetMapping("/sign-up")
    String userViewSingUp() {
        return USER_VIEW_SIGN_UP;
    }
    @PostMapping("/sign-up")
    String signUp(User newUser){ // 회원가입
        if(cafeUserService.signUp(newUser)) {
            return USER_REDIRECT_LIST;
        }
        return USER_REDIRECT_SIGN_UP_FAIL;
    }

    @GetMapping("/list")
    String getUserList (Model model) { // 유저 목록
        List<User> userList = cafeUserService.getUserList();
        model.addAttribute("userList", userList);
        model.addAttribute("userCnt", CollectionHelper.getItemNumberOfList(userList));
        return USER_VIEW_LIST;
    }

    @GetMapping("/profile/{userId}")
    String getUserProfile (Model model, @PathVariable("userId") String userId) { // 유저 프로필
        User user = cafeUserService.getUserProfile(userId);
        if( user != null) {
            model.addAttribute("user", user);
        }
        return USER_VIEW_PROFILE;
    }
}
