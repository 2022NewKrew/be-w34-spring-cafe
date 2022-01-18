package com.kakao.cafe.controller;

import com.kakao.cafe.controller.session.AuthInfo;
import com.kakao.cafe.controller.viewdto.request.UserCreateRequest;
import com.kakao.cafe.controller.viewdto.request.UserLoginRequest;
import com.kakao.cafe.controller.viewdto.request.UserUpdateRequest;
import com.kakao.cafe.controller.viewdto.response.UserListResponse;
import com.kakao.cafe.controller.viewdto.response.UserProfileResponse;
import com.kakao.cafe.user.service.UserService;
import com.kakao.cafe.user.service.dto.UserProfileServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    @PutMapping("")
    public String updateUserInfo2(@ModelAttribute UserUpdateRequest req) {
        log.info("PUT /user");
        try{
            log.info("{} {} {} {}",req.getOldPassword(), req.getNewPassword(), req.getName(), req.getEmail());
            userService.updateUserInfo(req.getUserId(), req.getOldPassword(), req.getNewPassword(), req.getName(), req.getEmail());
        } catch (IllegalArgumentException e) {
            log.info(e.getMessage());
            return "user/update_failed";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String userLogin(Model model) {
        log.info("GET /user/login");
        return "user/login";
    }

    @PostMapping("/login")
    public String tryLogin(@ModelAttribute UserLoginRequest req, HttpSession session) {
        log.info("POST /user/login : {} {}", req.getStringId(), req.getPassword());
        Long id = userService.validateUser(req.getStringId(), req.getPassword());
        UserProfileServiceResponse profile = userService.getUserProfile(req.getStringId());
        AuthInfo authInfo = AuthInfo.builder().id(id).stringId(profile.getStringId()).name(profile.getName()).build();
        session.setAttribute("authInfo", authInfo);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String tryLogout(Model model, HttpSession session) {
        log.info("GET /user/logout");
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        if (authInfo!= null) {
            log.info("Logout user : {}", authInfo.getId());
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String userSingup(Model model) {
        log.info("GET /user/signup");
        return "user/form";
    }

    @GetMapping("/profile/{id}")
    public String userProfile(@PathVariable("id") String stringId, Model model) {
        log.info("GET /user/profile/{}", stringId);
        UserProfileServiceResponse res = userService.getUserProfile(stringId);
        model.addAllAttributes(new UserProfileResponse(res.getStringId(), res.getName(), res.getEmail()));
        return "user/profile";
    }

    @GetMapping("/update")
    public String userUpdate(Model model, HttpSession session) {
        log.info("GET /user/update");
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        if (authInfo == null) {
            return "redirect:/user/login";
        }
        UserProfileServiceResponse res = userService.getUserProfile(authInfo.getStringId());
        model.addAllAttributes(new UserProfileResponse(res.getStringId(), res.getName(), res.getEmail()));
        return "user/update";
    }

    @GetMapping("/update/{stringId}")
    public String userUpdateById(@PathVariable String stringId, Model model, HttpSession session) {
        log.info("GET /user/update/{}", stringId);
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        if (authInfo == null) {
            throw new IllegalArgumentException("로그인 하시기 바랍니다.");
        }
        if (!stringId.equals(authInfo.getStringId())) {
            throw new IllegalArgumentException("자신의 회원정보만 수정 가능합니다.");
        }

        UserProfileServiceResponse res = userService.getUserProfile(authInfo.getStringId());
        model.addAllAttributes(new UserProfileResponse(res.getStringId(), res.getName(), res.getEmail()));
        return "user/update";
    }
}
