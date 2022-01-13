package com.kakao.cafe.controller;

import com.kakao.cafe.controller.viewdto.request.UserCreateRequest;
import com.kakao.cafe.controller.viewdto.request.UserUpdateRequest;
import com.kakao.cafe.controller.viewdto.response.UserListResponse;
import com.kakao.cafe.controller.viewdto.response.UserProfileResponse;
import com.kakao.cafe.controller.viewdto.response.UserUpdateResponse;
import com.kakao.cafe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final Long fakeSession = 0L;

    @GetMapping("")
    public String list(Model model) {
        log.info("GET /user access");
        model.addAllAttributes(new UserListResponse(userService.getAllUserViewData(0L)));
        return "user/list";
    }

    @PostMapping("")
    public String createUser(@ModelAttribute UserCreateRequest req) {
        log.info("POST /user access");
        userService.createUser(req.getUserId(), req.getEmail(), req.getName(), req.getPassword());
        return "redirect:user/";
    }

    @GetMapping("/profile/{id}")
    public String userProfile(@PathVariable("id") String stringId, Model model) {
        log.info("GET /user/profile/{}", stringId);
        model.addAllAttributes(new UserProfileResponse(userService.getUserProfile(stringId)));
        return "user/profile";
    }

    @GetMapping("/login")
    public String userLogin(Model model) {
        log.info("GET /user/login");
        return "user/login";
    }

    @GetMapping("/signup")
    public String userSingup(Model model) {
        log.info("GET /user/signup");
        return "user/form";
    }

    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable("id") String userStringId,  Model model) {
        log.info("GET /user/update id: {}", userStringId);
        model.addAllAttributes(new UserUpdateResponse(userService.getUserProfile(userStringId)));
//        model.addAllAttributes(new UserUpdateResponse(userService.getUserProfile(fakeSession)));
        return "user/update";
    }
    // 아무나 프로필 수정 가능해야 한다.

    @PostMapping("update")
    public String updateUserInfo(@ModelAttribute UserUpdateRequest req) {
        log.info("POST /user/update");
        try{
            log.info("{} {} {} {}",req.getOldPassword(), req.getNewPassword(), req.getName(), req.getEmail());
            userService.updateUserInfo(req.getUserId(), req.getOldPassword(), req.getNewPassword(), req.getName(), req.getEmail());
        } catch (IllegalArgumentException e) {
            log.info(e.getMessage());
            return "user/update_failed";
        }
        return "redirect:/";
    }



}
