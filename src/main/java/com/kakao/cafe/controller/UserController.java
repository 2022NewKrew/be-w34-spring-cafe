package com.kakao.cafe.controller;

import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kakao.cafe.config.Constant;
import com.kakao.cafe.controller.interceptor.AuthInfoCheck;
import com.kakao.cafe.controller.session.AuthInfo;
import com.kakao.cafe.controller.viewdto.UserControllerResponseMapper;
import com.kakao.cafe.controller.viewdto.request.UserCreateRequest;
import com.kakao.cafe.controller.viewdto.request.UserLoginRequest;
import com.kakao.cafe.controller.viewdto.request.UserUpdateRequest;
import com.kakao.cafe.user.service.UserService;
import com.kakao.cafe.user.service.dto.AllUserProfileServiceResponse;
import com.kakao.cafe.user.service.dto.UserProfileServiceResponse;

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
        try {
            log.info("{} {} {} {}", req.getOldPassword(), req.getNewPassword(), req.getName(), req.getEmail());
            userService.updateUserInfo(req.getUserId(), req.getOldPassword(), req.getNewPassword(), req.getName(),
                                       req.getEmail());
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
        session.setAttribute(Constant.authAttributeName, authInfo);
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
    public String userUpdate(Model model,
                             @SessionAttribute(Constant.authAttributeName) AuthInfo authInfo) {
        log.info("GET /user/update");
        UserProfileServiceResponse res = userService.getUserProfile(authInfo.getStringId());
        model.addAttribute("stringId", res.getStringId());
        model.addAttribute("name", res.getName());
        model.addAttribute("email", res.getEmail());
        return "user/update";
    }

    @GetMapping("/update/{stringId}")
    @AuthInfoCheck
    public String userUpdateById(@PathVariable String stringId, Model model,
                                 @SessionAttribute(Constant.authAttributeName) AuthInfo authInfo) {
        log.info("GET /user/update/{}", stringId);
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
