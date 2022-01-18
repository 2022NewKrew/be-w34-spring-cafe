package com.kakao.cafe.web;

import com.kakao.cafe.dto.CreateUserDto;
import com.kakao.cafe.dto.EditUserDto;
import com.kakao.cafe.dto.LoginUserDto;
import com.kakao.cafe.dto.ShowUserDto;
import com.kakao.cafe.service.LoginService;
import com.kakao.cafe.service.LogoutService;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final LoginService loginService;
    private final LogoutService logoutService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserController(UserService userService, LoginService loginService, LogoutService logoutService) {
        this.userService = userService;
        this.loginService = loginService;
        this.logoutService = logoutService;
    }


    @GetMapping("/form")
    public String userForm() {
        return "user/form";
    }

    @PostMapping("")
    public String userCreate(CreateUserDto createUserDto) {
        userService.save(createUserDto);
        return "redirect:/user";
    }

    @GetMapping("")
    public String userList(Model model, HttpSession httpSession) {
        List<ShowUserDto> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "user/list";
    }

    @GetMapping("/profile")
    public String userProfile() {
        return "user/profile";
    }

    @GetMapping("/{userId}")
    public String userListProfile(Model model, @PathVariable String userId) {
        ShowUserDto user = userService.findById(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/editform")
    public String editUser() {
        return "user/editform";
    }

    @PostMapping("/{userId}")
    public String changeUser(HttpSession httpsession, EditUserDto editUserDto, Model model, @PathVariable String userId) {
        userService.editUser(httpsession, editUserDto);
        ShowUserDto user = userService.findById(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }


    @GetMapping("/login")
    public String userLogin() {
        return "user/login";
    }

    @PostMapping("/login")
    public String userLoginAuth(LoginUserDto loginUserDto, HttpSession httpSession) {
        loginService.loginCheck(loginUserDto, httpSession);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String userLogout(HttpSession httpSession) {
        logoutService.logout(httpSession);
        return "redirect:/";
    }
}
