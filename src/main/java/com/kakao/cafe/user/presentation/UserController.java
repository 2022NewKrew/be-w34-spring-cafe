package com.kakao.cafe.user.presentation;

import com.kakao.cafe.user.application.UserService;
import com.kakao.cafe.user.dto.UserListResponse;
import com.kakao.cafe.user.dto.UserProfileResponse;
import com.kakao.cafe.user.dto.UserSaveRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.kakao.cafe.user.presentation.UserController.USER_URI;

@Controller
@Slf4j
@RequestMapping(USER_URI)
public class UserController {

    private final UserService userService;

    private final List<UserListResponse> currentUsers = new ArrayList<>();

    public static final String USER_URI = "/users";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public String save(@ModelAttribute UserSaveRequest request) {
        log.info(this.getClass() + ": 회원 가입");
        userService.save(request);
        return "redirect:/users";
    }

    @GetMapping()
    public ModelAndView findAll(Map<String, Object> model) {
        log.info(this.getClass() + ": 회원 목록");
        userService.findAll(model);
        return new ModelAndView("user/list", model);
    }

    @GetMapping("/{userId}")
    public ModelAndView findById(@PathVariable String userId, Map<String, Object> model) {
        log.info(this.getClass() + "회원 프로필");
        userService.findById(userId, model);
        return new ModelAndView("user/profile", model);
    }
}
