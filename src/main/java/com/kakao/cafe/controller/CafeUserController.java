package com.kakao.cafe.controller;


import com.kakao.cafe.annotation.LoginUser;
import com.kakao.cafe.helper.CollectionHelper;
import com.kakao.cafe.model.User;
import com.kakao.cafe.service.CafeUserService;
import lombok.extern.java.Log;
import org.springframework.lang.NonNull;
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
    private static final String USER_VIEW_SIGN_UP = USER_DIRECTORY+"/form";
    private static final String USER_VIEW_SIGN_IN = USER_DIRECTORY+"/login";
    private static final String USER_VIEW_LIST = USER_DIRECTORY+"/list";
    private static final String USER_VIEW_PROFILE = USER_DIRECTORY+"/profile";
    private static final String USER_VIEW_PROFILE_EDIT_ADMIN = USER_DIRECTORY+"/profile_admin";
    private static final String USER_VIEW_PROFILE_EDIT_FORM = USER_DIRECTORY+"/profile_edit";

    private static final String REDIRECT_PREFIX = "redirect:";
    private static final String USER_REDIRECT_SIGN_IN_SUCCESS = REDIRECT_PREFIX+"/";
    private static final String USER_REDIRECT_SIGN_IN_FAIL = REDIRECT_PREFIX+"/users/sign-in/fail";
    private static final String USER_REDIRECT_LIST = REDIRECT_PREFIX+"/users/list";
    private static final String USER_REDIRECT_SIGN_UP_FAIL = REDIRECT_PREFIX+"/users/sign-up/fail";
    private static final String USER_REDIRECT_SIGN_OUT = REDIRECT_PREFIX+"/";
    private static final String USER_REDIRECT_PROFILE_EDIT_ADMIN_FAIL = REDIRECT_PREFIX+"/users/profile/edit/fail";
    private static final String USER_REDIRECT_PROFILE_EDIT = REDIRECT_PREFIX+"/users/list";
    private static final String USER_REDIRECT_PROFILE_DELETE_SUCCESS = REDIRECT_PREFIX+"/";
    private static final String USER_REDIRECT_PROFILE_DELETE_FAIL = REDIRECT_PREFIX+"/users/profile/edit/fail";


    @GetMapping("/sign-in")
    String userViewSignIn() {
        return USER_VIEW_SIGN_IN;
    }

    @PostMapping("/sign-in")
    String signIn(@LoginUser String loginUser, HttpSession httpSession, User signInUser) {
        if(loginUser == null && cafeUserService.SignIn(signInUser)) {
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
    String signUp(@LoginUser String loginUser, User newUser){ // 회원가입
        if(loginUser == null && cafeUserService.signUp(newUser)) {
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
    String getUserProfile (Model model, @LoginUser String loginUser, @PathVariable("userId") String userId) { // 유저 프로필
        User user = cafeUserService.getUserProfile(userId);
        if(user != null) {
            model.addAttribute("user", user);
            if( loginUser != null ) {
                boolean canEdit = loginUser.equals(user.getUserId());
                model.addAttribute("canEdit", canEdit);
            }
        }
        return USER_VIEW_PROFILE;
    }

    @DeleteMapping("/profile/{userId}")
    String deleteProfile (@LoginUser String loginUser, HttpSession httpSession, @NonNull @PathVariable("userId") String userId) {
        if(loginUser != null && loginUser.equals(userId) && cafeUserService.deleteProfile(userId)) {
            httpSession.invalidate();
            return USER_REDIRECT_PROFILE_DELETE_SUCCESS;
        }
        return USER_REDIRECT_PROFILE_DELETE_FAIL;
    }

    @GetMapping("/profile/edit")
    String userViewProfile () {
        return USER_VIEW_PROFILE_EDIT_ADMIN;
    }

    @PostMapping("/profile/edit")
    String adminEditProfile (@LoginUser String loginUser, String password) {
        if(cafeUserService.adminEditProfile(loginUser, password)) {
            return USER_VIEW_PROFILE_EDIT_FORM;
        }
        return USER_REDIRECT_PROFILE_EDIT_ADMIN_FAIL;
    }

    @PutMapping("/profile/edit")
    String editProfile (@LoginUser String loginUser, HttpSession httpSession, User updateUser) {
        if(cafeUserService.editProfile(loginUser, updateUser)) {
            User user = (User) httpSession.getAttribute("signInUser");
            user.setEmail(updateUser.getEmail());
            user.setName(updateUser.getName());
            httpSession.setAttribute("signInUser", user);
            return USER_REDIRECT_PROFILE_EDIT;
        }
        return USER_REDIRECT_PROFILE_EDIT_ADMIN_FAIL;
    }

    @PostMapping("/sign-out")
    String signOut(HttpSession httpSession) {
        httpSession.invalidate();
        return USER_REDIRECT_SIGN_OUT;
    }
}
