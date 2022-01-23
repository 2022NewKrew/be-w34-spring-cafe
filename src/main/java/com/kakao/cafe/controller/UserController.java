package com.kakao.cafe.controller;

import com.kakao.cafe.application.dto.UserDto;
import com.kakao.cafe.application.service.UserService;
import com.kakao.cafe.util.annotation.LoginCheck;
import com.kakao.cafe.util.exception.UserDuplicatedException;
import com.kakao.cafe.util.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String goRegisterView() {
        return "user/register";
    }

    @GetMapping("/login")
    public String goLoginView() {
        return "user/login";
    }

    @LoginCheck
    @GetMapping("/logout")
    public String userLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    @GetMapping("/list")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "/user/list";
    }

    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    @GetMapping("/{userId}")
    public String userView(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user", userService.findUserByUserId(userId));
        return "/user/view";
    }

    @LoginCheck
    @GetMapping("/{userId}/edit")
    public String goUserModifyView(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user", userService.findUserByUserId(userId));
        return "user/edit";
    }

    @PostMapping("/register")
    public String userRegister(UserDto userDto, Model model) {
        try {
            userService.registerUser(userDto);
        } catch (UserDuplicatedException e) {
            model.addAttribute("user", userDto);
            model.addAttribute("idErrorMessage", e.getMessage());
            return "user/register";
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String userLogin(String userId, String password, HttpSession session, Model model) {
        UserDto userDto;

        try {
            userDto = userService.findUserByLoginInfo(userId, password, "일치하는 회원 정보가 없습니다.");
        } catch (UserNotFoundException e) {
            model.addAttribute("userId", userId);
            model.addAttribute("UserNotFoundErrorMessage", e.getMessage());
            return "/user/login";
        }

        session.setAttribute("USER_ID", userId);
        session.setAttribute("USER_NAME", userDto.getName());

        if ("admin".equals(userId)) {
            session.setAttribute("IS_ADMIN", true);
        }

        return "redirect:/";
    }

    @LoginCheck
    @PutMapping("/{userId}/edit")
    public String userModify(UserDto userDto, String newPassword, Model model) {
        try {
            userService.findUserByLoginInfo(userDto.getUserId(), userDto.getPassword(), "비밀번호가 일치하지 않습니다.");
        } catch (UserNotFoundException e) {
            model.addAttribute("user", userDto);
            model.addAttribute("notMatchedErrorMessage", e.getMessage());
            return "user/edit";
        }

        userService.modifyUser(UserDto.builder()
                .userId(userDto.getUserId())
                .password(newPassword)
                .name(userDto.getName())
                .email(userDto.getEmail()).build());
        return "redirect:/";
    }
}
