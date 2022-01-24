package com.kakao.cafe.user.presentation;

import com.kakao.cafe.user.application.UserService;
import com.kakao.cafe.user.application.dto.UserListResponse;
import com.kakao.cafe.user.application.dto.UserProfileResponse;
import com.kakao.cafe.user.application.dto.UserSaveRequest;
import com.kakao.cafe.user.application.dto.UserUpdateRequest;
import com.kakao.cafe.user.domain.SessionedUser;
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
@RequestMapping(USER_URI)
public class UserController {

    private final UserService userService;

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
        List<UserListResponse> userListResponses = userService.findAll();
        model.put("users", userListResponses);
        return new ModelAndView("user/list", model);
    }

    @GetMapping("/{userId}")
    public ModelAndView findById(@PathVariable String userId, Map<String, Object> model) {
        log.info(this.getClass() + ": 회원 프로필");
        UserProfileResponse userProfileResponse = userService.findById(userId);
        model.put("user", userProfileResponse);
        return new ModelAndView("user/profile", model);
    }

    @GetMapping("/{userId}/form")
    public ModelAndView findFormById(
            @PathVariable String userId,
            Map<String, Object> model,
            HttpSession session
    ) {
        log.info(this.getClass() + ": 개인정보 수정 폼");
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            return new ModelAndView("redirect:/auth/login");
        }
        UserProfileResponse userProfileResponse = userService.findById(userId);
        model.put("user", userProfileResponse);
        return new ModelAndView("user/updateForm", model);
    }

    @PutMapping("/{userId}")
    public String updateById(
            @PathVariable String userId,
            UserUpdateRequest userUpdateRequest,
            HttpSession session
    ) {
        log.info(this.getClass() + ": 개인정보 수정");
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            return "redirect:/auth/login";
        }

        SessionedUser user = (SessionedUser) value;
        userService.updateById(userId, userUpdateRequest, user);
        return "redirect:/users";
    }
}
