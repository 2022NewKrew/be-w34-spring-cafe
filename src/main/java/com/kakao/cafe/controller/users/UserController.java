package com.kakao.cafe.controller.users;

import com.kakao.cafe.controller.users.dto.*;
import com.kakao.cafe.controller.users.mapper.UserDtoMapper;
import com.kakao.cafe.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService usersService) {
        this.userService = usersService;
    }

    @GetMapping()
    public String list(Model model) {
        List<UserItemDto> users = userService.getUsersAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    @PostMapping()
    public String signUp(SignUpRequestDto signUpRequestDto) {
        userService.signUp(signUpRequestDto.getUserId(),
                signUpRequestDto.getPassword(),
                signUpRequestDto.getName(),
                signUpRequestDto.getEmail());
        return "redirect:/users";
    }

    @GetMapping("{userId}")
    public String profile(@PathVariable String userId, Model model) {
        UserProfileDto userProfile = userService.getUserProfile(userId);
        logger.info(userProfile.toString());
        model.addAttribute("user", userProfile);
        return "user/profile";
    }

    @GetMapping("{id}/form")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        UserInfoDto userInfo = userService.getUserInfo(id);
        logger.info(userInfo.toString());
        model.addAttribute("user", userInfo);
        return "user/updateForm";
    }

    @PostMapping("{id}/update")
    public String updateUser(@PathVariable Long id, UpdateRequestDto updateRequestDto) {
        userService.updateUser(UserDtoMapper.toUserUpdateForm(id, updateRequestDto));
        return "redirect:/users";
    }
}
