package com.kakao.cafe.user.presentation;

import com.kakao.cafe.user.application.UserService;
import com.kakao.cafe.user.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static com.kakao.cafe.user.presentation.UserController.USER_URI;

@Controller
@Slf4j
//@RequestMapping(USER_URI)
public class UserController {

    private final UserService userService;

    public static final String USER_URI = "/users";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(USER_URI)
    public String save(@ModelAttribute UserSaveRequest request) {
        log.info(this.getClass() + ": 회원 가입");
        userService.save(request);
        return "redirect:/users";
    }

    @GetMapping(USER_URI)
    public ModelAndView findAll(Map<String, Object> model) {
        log.info(this.getClass() + ": 회원 목록");
        List<UserListResponse> userListResponses = userService.findAll();
        model.put("users", userListResponses);
        return new ModelAndView("user/list", model);
    }

    @GetMapping(USER_URI + "/{userId}")
    public ModelAndView findById(@PathVariable String userId, Map<String, Object> model) {
        log.info(this.getClass() + ": 회원 프로필");
        UserProfileResponse userProfileResponse = userService.findById(userId);
        model.put("user", userProfileResponse);
        return new ModelAndView("user/profile", model);
    }

    @GetMapping(USER_URI + "/{userId}/form")
    public ModelAndView findFormById(@PathVariable String userId, Map<String, Object> model) {
        log.info(this.getClass() + ": 개인정보 수정 폼");
        UserProfileResponse userProfileResponse = userService.findById(userId);
        model.put("user", userProfileResponse);
        return new ModelAndView("user/updateForm", model);
    }

    // Use @PutMapping
    @PostMapping(USER_URI + "/{userId}/updates")
    public String updateById(@PathVariable String userId, UserUpdateRequest userUpdateRequest) {
        log.info(this.getClass() + ": 개인정보 수정");
        userService.updateById(userId, userUpdateRequest);
        return "redirect:/users";
    }

    @PostMapping("/login")
    public String loginById(UserLoginRequest userLoginRequest, HttpSession session) {
        log.info(this.getClass() + ": 회원 로그인");
        UserLoginResponse userLoginResponse = userService.loginById(userLoginRequest);
        session.setAttribute("sessionedUser", userLoginResponse);
        return "redirect:/";
    }
}
