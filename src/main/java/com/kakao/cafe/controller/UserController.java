package com.kakao.cafe.controller;

import com.kakao.cafe.core.meta.SessionData;
import com.kakao.cafe.domain.user.UserService;
import com.kakao.cafe.domain.user.dto.UserUpdateDto;
import com.kakao.cafe.domain.user.dto.UserTableRowDto;
import com.kakao.cafe.domain.user.dto.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String showUserList(Model model) {
        final List<UserTableRowDto> users = userService.retrieveAll();
        model.addAttribute("users", users);
        return "/user/list";
    }

    @GetMapping("/{id}")
    public String showProfile(Model model,
                              @PathVariable("id") Long id) {
        final UserTableRowDto user = userService.retrieveById(id);
        model.addAttribute("user", user);
        return "/user/profile";
    }

    @GetMapping("/signup")
    public String initSignupForm() {
        return "/user/signup_form";
    }

    @PostMapping("/")
    public String ProceedSignupForm(HttpSession session,
                                    UserSignUpDto dto) {
        Long id = userService.signUp(dto);
        session.setAttribute(SessionData.USER_KEY, id);
        session.setAttribute(SessionData.USER_NAME, dto.getNickname());
        return "redirect:/users/";
    }

    @GetMapping("/{id}/update")
    public String initUpdateForm(Model model,
                                 HttpSession session) {
        Long userKey = (Long) session.getAttribute(SessionData.USER_KEY);
        final UserTableRowDto user = userService.retrieveById(userKey);
        model.addAttribute("user", user);
        return "/user/update_form";
    }

    @PutMapping("/{id}")
    public String processUpdateForm(HttpSession session,
                                    UserUpdateDto dto) {
        userService.updateProfile(dto);
        session.setAttribute(SessionData.USER_NAME, dto.getNickname());
        return "redirect:/users/" + dto.getId();
    }
}
