package com.kakao.cafe.web;

import com.kakao.cafe.service.UsersService;
import com.kakao.cafe.web.dto.UserResponseDto;
import com.kakao.cafe.web.dto.UsersCreateRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
public class UsersController {

    private final UsersService usersService = new UsersService();

    @GetMapping("/users")
    public String users(Model model) {
        ArrayList<UserResponseDto> users = usersService.findAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/users/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        UserResponseDto user = usersService.findById(id);
        model.addAttribute("user", user);
        return "user/updateForm";
    }

    @PostMapping("/api/users")
    public String Signin(UsersCreateRequestDto requestDto) {
        usersService.save(requestDto);
        return "redirect:/users";
    }

    @PutMapping("/api/users/{id}/update")
    public String update(@PathVariable("id") Long id, UsersCreateRequestDto requestDto, HttpSession session) {
        UserResponseDto userInfo = (UserResponseDto) session.getAttribute("sessionedUser");
        String email = userInfo.getEmail();
        String password = userInfo.getPassword();
        if (requestDto.checkUserInfo(email, password)) { usersService.update(id, requestDto); }
        return "redirect:/users";
    }

    @PostMapping("/api/login")
    public String login(String email, String password, HttpSession session) {
        UserResponseDto userInfo = usersService.findByEmail(email);
        if (userInfo.checkLoginInfo(email, password)) {
            session.setAttribute("sessionedUser", userInfo);
            System.out.println("login success");
            return "redirect:/";
        } else {
            System.out.println("login failed");
            return "redirect:/";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/login-success")
    public String login_success() {
        return "user/success";
    }

    @GetMapping("/signin")
    public String signin() {
        return "user/form";
    }

}
