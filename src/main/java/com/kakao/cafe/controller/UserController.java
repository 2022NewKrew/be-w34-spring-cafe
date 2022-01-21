package com.kakao.cafe.controller;

import com.kakao.cafe.util.auth.LoginCheck;
import com.kakao.cafe.dto.user.SessionUser;
import com.kakao.cafe.dto.user.UserRequest;
import com.kakao.cafe.dto.user.UserUpdateReqDto;
import com.kakao.cafe.service.user.UserService;
import com.kakao.cafe.util.exception.UnauthorizedException;
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
    public String createUser(@ModelAttribute UserRequest userRequest) {
        userService.addUser(userRequest);
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

        if(sessionUser == null || !sessionUser.getId().equals(id)){
            throw new UnauthorizedException("로그인하지 않았거나, 수정 권한이 없습니다.");
        }

        model.addAttribute("user", userService.findUserById(id));
        return "user/updateForm";
    }

    @PutMapping("/{id}")
    public String amendUser(@PathVariable Long id, @ModelAttribute UserUpdateReqDto userUpdateReqDto, HttpSession session) {
        userUpdateReqDto.setId(id);
        userService.modifyUser(userUpdateReqDto);
        session.setAttribute("sessionedUser", userService.findUserById(id));
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }

    @PostMapping("/login")
    public String loginUser(String userId, String password, HttpSession session) {
        UserRequest userRequest = UserRequest.builder()
                .userId(userId)
                .password(password)
                .build();
        session.setAttribute("sessionedUser", userService.login(userRequest));
        return "redirect:/users";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session, @LoginCheck SessionUser sessionUser) {
        if(sessionUser == null){
            return "redirect:/users/login";
        }
        session.invalidate();
        return "redirect:/";
    }


}
