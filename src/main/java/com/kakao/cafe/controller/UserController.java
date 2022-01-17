package com.kakao.cafe.controller;

import com.kakao.cafe.model.user.UserDto;
import com.kakao.cafe.model.user.UserSignupRequest;
import com.kakao.cafe.model.user.UserUpdateRequest;
import com.kakao.cafe.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public String signup(@Valid UserSignupRequest user, RedirectAttributes rttr) {
        userService.signupUser(user);
        rttr.addFlashAttribute("msg", "회원가입에 성공하였습니다.");
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("sizeOfUsers", users.size());
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/profile";
    }

    @GetMapping("/users/{id}/update")
    public String getUpdateForm(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/updateForm";
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable long id, @Valid UserUpdateRequest request, RedirectAttributes rttr) {
        userService.updateUser(id, request);
        rttr.addFlashAttribute("msg", "회원 정보를 수정하였습니다.");
        return "redirect:/users";
    }

}
