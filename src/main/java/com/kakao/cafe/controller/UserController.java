package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.user.UserSaveDto;
import com.kakao.cafe.dto.user.UserUpdateDto;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public String save(@ModelAttribute() UserSaveDto userSaveDto) {
        userService.save(userSaveDto);
        return "redirect:/";
    }

    @PutMapping("/users/{id}")
    public String update(@PathVariable int id, @ModelAttribute() UserUpdateDto userUpdateDto, HttpSession session) {
        User user = (User)session.getAttribute("sessionedUser");
        if (user.getId() != id){
            throw new IllegalArgumentException("로그인된 사용자 정보와 수정하려는 사용자 정보가 다릅니다.");
        }
        session.setAttribute("sessionedUser", userService.update(id, userUpdateDto));
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/users/{id}")
    public String findbyId(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findbyId(id));
        return "user/profile";
    }

    @GetMapping("/users/{id}/form")
    public String serveUpdateForm(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findbyId(id));
        return "user/updateForm";
    }

    @PostMapping("/users/login")
    public String login(String stringId, String password, HttpSession session) {
        User user;
        try {
            user = userService.login(stringId, password);
        } catch(IllegalArgumentException e){
            return "/user/login_failed";
        }
        session.setAttribute("sessionedUser", user);
        return "redirect:/";
    }

    @GetMapping("/users/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
