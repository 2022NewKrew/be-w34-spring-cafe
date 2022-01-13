package com.kakao.cafe.controller;


import com.kakao.cafe.helper.CollectionHelper;
import com.kakao.cafe.model.User;
import com.kakao.cafe.service.CafeUserService;
import com.kakao.cafe.url.UserRedirect;
import com.kakao.cafe.url.UserView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(UserRedirect.USER_BASE_URL)
public class CafeUserController {

    final CafeUserService cafeUserService;

    public CafeUserController (CafeUserService cafeUserService) {
        this.cafeUserService = cafeUserService;
    }

    @GetMapping("/sign-in")
    String userViewSignIn() {
        return UserView.USER_VIEW_SIGN_IN;
    }
    @GetMapping("/sign-up")
    String userViewSingUp() {
        return UserView.USER_VIEW_SIGN_UP;
    }
    @GetMapping("/sign-up/fail")
    String userViewSignUpFail() {
        return UserView.USER_VIEW_SIGN_UP_FAIL;
    }

    @PostMapping("/sign-up")
    String signUp(User newUser){ // 회원가입
        if(cafeUserService.signUp(newUser)) {
            return UserRedirect.USER_REDIRECT_LIST;
        }
        return UserRedirect.USER_REDIRECT_SIGN_UP_FAIL;
    }

    @GetMapping("/list")
    String getUserList (Model model) { // 유저 목록
        List<User> userList = cafeUserService.getUserList();
        model.addAttribute("userList", userList);
        model.addAttribute("userCnt", CollectionHelper.getItemNumberOfList(userList));
        return UserView.USER_VIEW_LIST;
    }

    @GetMapping("/profile/{userId}")
    String getUserProfile (Model model, @PathVariable("userId") String userId) { // 유저 프로필
        User user = cafeUserService.getUserProfile(userId);
        if( user != null) {
            model.addAttribute("user", user);
        }
        return UserView.USER_VIEW_PROFILE;
    }
}
