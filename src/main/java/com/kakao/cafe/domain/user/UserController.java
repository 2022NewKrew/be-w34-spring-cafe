package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.dto.UserProfileDto;
import com.kakao.cafe.domain.user.dto.UserResponseDto;
import com.kakao.cafe.domain.user.dto.UserSignUpDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserListService userListService;

    private final UserSignUpService userSignUpService;

    private final UserProfileService userProfileService;

    public UserController(UserListService userListService, UserSignUpService userSignUpService, UserProfileService userProfileService) {
        this.userListService = userListService;
        this.userSignUpService = userSignUpService;
        this.userProfileService = userProfileService;
    }

    @GetMapping("/user")
    public String showUserList(Model model) {
        List<UserResponseDto> users = userListService.retrieveAllUsers();
        model.addAttribute("totalSize", users.size());
        model.addAttribute("users", users);
        return "/user/list";
    }

    @GetMapping("/user/{userId}")
    public String showUserProfile(Model model,
                                  UserProfileDto dto) {
        UserResponseDto user = userProfileService.show(dto);
        model.addAttribute("user", user);
        return "/user/profile";
    }

    @GetMapping("/user/signup")
    public String initSignupForm() {
        return "/user/signupForm";
    }

    @PostMapping("/user/signup")
    public String ProceedSignupForm(UserSignUpDto dto) {
        userSignUpService.signUp(dto);
        return "redirect:/user";
    }

    @GetMapping("/user/{userId}/update")
    public String initUpdateForm(Model model,
                                 UserProfileDto dto,
                                 @PathVariable("userId") String userId) {
        model.addAttribute("userId", userId);
        model.addAttribute("user", userListService.retrieveUser(dto));
        return "/user/updateForm";
    }

    @PostMapping("/user/{userId}/update")
    public String processUpdateForm(UserProfileDto dto) {
        userProfileService.update(dto);
        return "redirect:/user";
    }
}
