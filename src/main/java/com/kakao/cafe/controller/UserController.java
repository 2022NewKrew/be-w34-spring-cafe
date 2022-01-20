package com.kakao.cafe.controller;

import com.kakao.cafe.auth.LoginCheck;
import com.kakao.cafe.dto.user.SessionUser;
import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.dto.user.UserReqDto;
import com.kakao.cafe.dto.user.UserUpdateReqDto;
import com.kakao.cafe.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping
    public String showUserList(Model model) {
        model.addAttribute("users", userService.findUsers());
        return "user/list";
    }

    @PostMapping
    public String createUser(@ModelAttribute UserReqDto userReqDto) {
        userService.addUser(userReqDto);
        return "redirect:/users";
    }

    @GetMapping("/form")
    public String showForm() {
        return "user/form";
    }

    @GetMapping("/{id}")
    public String showId(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "user/profile";
    }

    @GetMapping("/{id}/form")
    public String showUpdateForm(@PathVariable Long id, Model model, @LoginCheck SessionUser sessionUser) {

        if(!sessionUser.getId().equals(id)){
            // 에러 페이지로 이동하게 수정
            return "redirect:/users/login";
        }

        model.addAttribute("user", userService.findUserById(id));
        return "user/updateForm";
    }

    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserUpdateReqDto userUpdateReqDto) {
        userUpdateReqDto.setId(id);
        userService.updateUser(userUpdateReqDto);
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }

    @PostMapping("/login")
    public String loginUser(String userId, String password, HttpSession session) {
        UserReqDto userReqDto = UserReqDto.builder()
                .userId(userId)
                .password(password)
                .build();
        session.setAttribute("sessionedUser", userService.login(userReqDto));
        return "redirect:/users";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session, @LoginCheck SessionUser sessionUser) {
        session.invalidate();
        return "redirect:/";
    }


}
