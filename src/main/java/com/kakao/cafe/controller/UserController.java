package com.kakao.cafe.controller;

import com.kakao.cafe.controller.aop.AuthInfoCheck;
import com.kakao.cafe.controller.session.AuthInfo;
import com.kakao.cafe.controller.session.HttpSessionUtil;
import com.kakao.cafe.controller.viewdto.UserControllerResponseMapper;
import com.kakao.cafe.controller.viewdto.request.UserCreateRequest;
import com.kakao.cafe.controller.viewdto.request.UserLoginRequest;
import com.kakao.cafe.controller.viewdto.request.UserUpdateRequest;
import com.kakao.cafe.user.service.UserService;
import com.kakao.cafe.user.service.dto.AllUserProfileServiceResponse;
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

    @GetMapping("")
    public String list(Model model) {
        log.info("GET /user access");
        AllUserProfileServiceResponse dto = userService.getAllUserViewData(0L);
//
//        model.addAllAttributes(new UserListResponse());
        model.addAttribute("users", UserControllerResponseMapper.getUserListResponse(dto));
        return "user/list";
    }

    @PostMapping("")
    public String createUser(@ModelAttribute UserCreateRequest req) {
        log.info("POST /user access");
        userService.createUser(req.getUserId(), req.getEmail(), req.getName(), req.getPassword());
        return "redirect:user/";
    }

    @PutMapping("")
    public String updateUserInfo(@ModelAttribute UserUpdateRequest req) {
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
    @AuthInfoCheck
    public String tryLogout(Model model, HttpSession session) {
        log.info("GET /user/logout");
        session.invalidate();
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
        model.addAttribute("stringId", res.getStringId());
        model.addAttribute("name", res.getName());
        model.addAttribute("email", res.getEmail());
        return "user/profile";
    }

    @GetMapping("/update")
    @AuthInfoCheck
    public String userUpdate(Model model, HttpSession session) {
        log.info("GET /user/update");
        AuthInfo authInfo = HttpSessionUtil.getAuthInfo(session);
        UserProfileServiceResponse res = userService.getUserProfile(authInfo.getStringId());
        model.addAttribute("stringId", res.getStringId());
        model.addAttribute("name", res.getName());
        model.addAttribute("email", res.getEmail());
        return "user/update";
    }

    @GetMapping("/update/{stringId}")
    @AuthInfoCheck
    public String userUpdateById(@PathVariable String stringId, Model model, HttpSession session) {
        log.info("GET /user/update/{}", stringId);
        AuthInfo authInfo = HttpSessionUtil.getAuthInfo(session);
        if (!stringId.equals(authInfo.getStringId())) {
            throw new IllegalArgumentException("자신의 회원정보만 수정 가능합니다.");
        }
        UserProfileServiceResponse res = userService.getUserProfile(authInfo.getStringId());
        model.addAttribute("stringId", res.getStringId());
        model.addAttribute("name", res.getName());
        model.addAttribute("email", res.getEmail());
        return "user/update";
    }
}
