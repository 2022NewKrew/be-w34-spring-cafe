package com.kakao.cafe.controller;

import com.kakao.cafe.DTO.SignInDTO;
import com.kakao.cafe.domain.UserDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create")
    public String SignUp(@ModelAttribute SignInDTO userInfo) {
        logger.debug("Attempt to SignUp; ID={}", userInfo.getUserId());

        if (userService.SignUp(userInfo)) {
            logger.debug("Successful SignUp; ID={}", userInfo.getUserId());
            return "redirect:/user/list";
        }

        logger.debug("SignUp Failed. already exist ID={}", userInfo.getUserId());
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.getUserInfoLst());
        return "user/list";
    }

    @GetMapping("/prof/{userId}")
    public String getUserProfile(Model model, @PathVariable String userId) {
        logger.debug("User profile request; ID={}", userId);
        model.addAttribute("userProfile", userService.getUserProfile(userId));
        return "user/profile";
    }
}
