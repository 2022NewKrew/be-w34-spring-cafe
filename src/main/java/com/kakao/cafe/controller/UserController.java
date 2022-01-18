package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.Auth;
import com.kakao.cafe.dto.user.*;
import com.kakao.cafe.exception.InputDataException;
import com.kakao.cafe.exception.LoginFailedException;
import com.kakao.cafe.exception.NullSessionException;
import com.kakao.cafe.exception.UserMismatchException;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller()
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String form() {
        return "./user/form";
    }

    @PostMapping("/signup")
    public String signUp(@Valid UserDto userDto, BindingResult bindingResult) throws InputDataException {
        if (bindingResult.hasErrors()) {
            throw new InputDataException("입력이 올바르지 않습니다.");
        }
        userService.addUser(userDto);
        logger.info("회원 등록 완료 : {}", userDto);
        return "redirect:/";
    }

    @GetMapping("/users")
    @Auth
    public String users(Model model) {
        List<UserInfoDto> users = userService.getUsers();
        model.addAttribute("users", users);
        return "./user/list";
    }

    @GetMapping("/{userId}")
    public String profile(@PathVariable String userId, Model model) {
        UserProfileDto matchedUser = userService.getUserProfile(userId);
        model.addAttribute("matchedUser", matchedUser);
        return "./user/profile";
    }

    @GetMapping("/login")
    public String login() {
        return "./user/login";
    }

    @GetMapping("/{userId}/update")
    public String getUpdateUserForm(@PathVariable String userId, Model model, HttpSession session) throws UserMismatchException {
        UserSessionDto sessionedUser = (UserSessionDto) session.getAttribute("sessionedUser");
        if (!userId.equals(sessionedUser.getUserId())) {
            throw new UserMismatchException("다른 사용자의 정보를 수정할 수 없습니다.");
        }
        UserInfoDto matchedUser = userService.getUser(userId);
        model.addAttribute("matchedUser", matchedUser);
        return "./user/updateForm";
    }

    @PutMapping("/{userId}/update")
    public String updateUser(@PathVariable String userId, String curPassword, String newPassword, String name, String email) throws InputDataException {
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setPassword(newPassword);
        userDto.setName(name);
        userDto.setEmail(email);
        userService.updateUser(userDto, curPassword);
        logger.info("회원 수정 완료 : {}", userDto);
        return "redirect:/user/users";
    }

    @PostMapping("/login")
    public String login(UserLoginDto userLoginDto, HttpSession session) throws LoginFailedException {
        UserSessionDto sessiondUser = userService.login(userLoginDto);
        session.setAttribute("sessionedUser",sessiondUser);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("sessionedUser");
        return "redirect:/";
    }

}
