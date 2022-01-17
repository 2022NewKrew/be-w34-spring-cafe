package com.kakao.cafe.controller;

import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.model.service.UserService;
import com.kakao.cafe.util.annotation.LoginCheck;
import com.kakao.cafe.util.exception.UserDuplicatedException;
import com.kakao.cafe.util.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        try {
            userService.findUserByLoginInfo(userId, password, "일치하는 회원 정보가 없습니다.");
        } catch (UserNotFoundException e) {
            model.addAttribute("userId", userId);
            model.addAttribute("UserNotFoundErrorMessage", e.getMessage());
            return "/user/login";
        }

        session.setAttribute("USER_ID", userId);

        if ("admin".equals(userId)) {
            session.setAttribute("IS_ADMIN", true);
        }

        return "redirect:/";
    }

    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    @GetMapping("/list")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "/user/list";
    }

    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    @GetMapping("/view/{userId}")
    public String userView(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user", userService.findUserByUserId(userId));
        return "/user/view";
    }

    @LoginCheck
    @PostMapping("/modify")
    public String goUserModifyView(String userId, Model model) {
        model.addAttribute("user", userService.findUserByUserId(userId));
        return "/user/modify";
    }

    @LoginCheck
    @PutMapping("/modify")
    public String userModify(UserDto userDto, String newPassword, Model model) {
        try {
            userService.findUserByLoginInfo(userDto.getUserId(), userDto.getPassword(), "비밀번호가 일치하지 않습니다.");
        } catch (UserNotFoundException e) {
            model.addAttribute("user", userDto);
            model.addAttribute("notMatchedErrorMessage", e.getMessage());
            return "/user/modify";
        }

        userService.modifyUser(UserDto.builder()
                .userId(userDto.getUserId())
                .password(newPassword)
                .name(userDto.getName())
                .email(userDto.getEmail()).build());
        return "redirect:/user/list";
    }
}
