package com.kakao.cafe.user.presentation;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserListResponse;
import com.kakao.cafe.user.dto.UserProfileResponse;
import com.kakao.cafe.user.dto.UserSaveRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.kakao.cafe.user.presentation.UserController.USER_URI;

@Controller
@RequestMapping(USER_URI)
public class UserController {
    private final List<UserListResponse> currentUsers = new ArrayList<>();

    public static final String USER_URI = "/users";

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping()
    public String save(@ModelAttribute UserSaveRequest request) {
        logger.info("회원 가입");
        User newUser = request.toUser();
        UserListResponse userResponse = new UserListResponse(currentUsers.size() + 1, newUser);
        currentUsers.add(userResponse);
        return "redirect:/users";
    }

    @GetMapping()
    public ModelAndView findAll(Map<String, Object> model) {
        logger.info("회원 목록");
        model.put("users", currentUsers);
        return new ModelAndView("user/list", model);
    }

    @GetMapping("/{userId}")
    public ModelAndView findById(@PathVariable String userId, Map<String, Object> model) {
        logger.info("회원 프로필");
        UserProfileResponse target = currentUsers.stream()
                .map(UserProfileResponse::valueOf)
                .filter(user -> user.isSameUser(userId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

        model.put("user", target);
        return new ModelAndView("user/profile", model);
    }
}
