package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.LoginService;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserService;
import com.kakao.cafe.domain.user.dto.UserForm;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    // 회원가입
    @PostMapping("")
    public String create(@Valid UserForm form, HttpSession session) {
        logger.info("POST /users");
        User newUser = User.of(form);
        User joinedUser = userService.join(newUser);
        session.setAttribute("sessionedUser", joinedUser);
        return "redirect:/users";
    }

    // 유저 목록
    @GetMapping("")
    public String list(Model model) {
        logger.info("GET /users");
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    // 유저 프로필
    @GetMapping("/{userId}")
    public String profile(@PathVariable String userId, Model model){
        logger.info("GET /users/{userId}");
        User user = userService.findByUserId(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    // 유저 업데이트 폼
    @GetMapping("/{userId}/form")
    public String updateForm(@PathVariable String userId, Model model){
        User user = userService.findByUserId(userId);
        model.addAttribute("user", user);
        return "user/updateForm";
    }

    // 유저 업데이트
    @PutMapping("/{userId}")
    public String updateUser(@PathVariable String userId, UserForm userForm){
        logger.info("PUT /{userId}/update");

        userForm.setUserId(userId);
        userService.updateUser(User.of(userForm));
        return "redirect:/users";
    }

    // 로그인
    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        User user = loginService.login(userId, password);
        session.setAttribute("sessionedUser", user);
        return "redirect:/";
    }

    // 로그아웃 (session 전부 삭제)
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
