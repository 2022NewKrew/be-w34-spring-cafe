package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public String userRegister(UserDto userDto, Model model) {
        UserDto registeredUser = userService.register(userDto);

        model.addAttribute("email", registeredUser.getEmail());
        model.addAttribute("nickName", registeredUser.getNickName());

        return "/user/register_success";
    }

    @GetMapping("/users")
    public String userList(Model model) {
        List<UserDto> userDtos = userService.allUsers();
        model.addAttribute("users", userDtos);

        return "/user/list";
    }

    @GetMapping("/users/{nickName}")
    public String userProfile(@PathVariable("nickName") String nickName, Model model) {

        return "temp";
    }

    @PostMapping("/users/login")
    public String userLogin(UserDto userDto, HttpSession httpSession, Model model) {
        try {
            UserDto loginUser = userService.login(userDto);
            System.out.println("loginUser.getId() + loginUser.getEmail() = " + loginUser.getId() + loginUser.getEmail());
            httpSession.setAttribute("sessionUserName", loginUser.getNickName());
            httpSession.setAttribute("sessionUserId", loginUser.getId());

            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());

            return "error/login_fail";
        }
    }
}
