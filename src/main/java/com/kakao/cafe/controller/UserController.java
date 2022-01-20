package com.kakao.cafe.controller;

import com.kakao.cafe.config.auth.LoginUser;
import com.kakao.cafe.dto.RequestUserDto;
import com.kakao.cafe.dto.SessionUser;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    /*
     * 회원가입
     */
    @PostMapping("/users")
    public String join(@ModelAttribute RequestUserDto userDto) {
        userService.join(userDto);
        return "redirect:/users";
    }

    /*
     * 유저 리스트 조회
     */
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("countOfUser", userService.getCountOfUser());
        return "user/list";
    }

    /*
     * 유저 상세 정보 조회
     */
    @GetMapping("/users/{id}")
    public String getUserProfile(@PathVariable long id, Model model, @LoginUser SessionUser user) {
        if (id == user.getId()) {
            model.addAttribute("myId", user.getId());
        }
        model.addAttribute("user", userService.getUserById(id));

        return "user/profile";
    }

    /*
     * 내 정보 수정 페이지 조회
     */
    @GetMapping("/users/me/form")
    public String showEditUserPage(Model model, @LoginUser SessionUser user) {
        model.addAttribute("user", userService.getUserById(user.getId()));
        return "user/updateForm";
    }

    /*
     * 내 정보 수정
     */
    @PutMapping("/users/me/form")
    public String editUser(@ModelAttribute RequestUserDto userDto, @LoginUser SessionUser user) {
        userService.updateUser(user.getId(), userDto);
        return "redirect:/users";
    }

    /*
     * 로그인
     */
    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        SessionUser user = userService.login(userId.trim(), password.trim());
        session.setAttribute("sessionedUser", user);
        return "redirect:/";
    }

    /*
     * 로그아웃
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}
