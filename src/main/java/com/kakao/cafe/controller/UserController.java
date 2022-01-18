package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.CheckAuth;
import com.kakao.cafe.domain.Auth;
import com.kakao.cafe.domain.dtos.UserResponseDto;
import com.kakao.cafe.domain.dtos.UserSaveDto;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.utils.SessionName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUserPage() {
        return "user/form";
    }

    @GetMapping("")
    public String userListPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @PostMapping("")
    public String createUser(
            @Valid @ModelAttribute UserSaveDto dto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "redirect:/users/create";
        }
        userService.save(dto);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String userDetailPage(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/profile";
    }

    @PutMapping("/{id}")
    public String updateUser(
            @Valid @ModelAttribute UserSaveDto dto,
            BindingResult bindingResult,
            @PathVariable Long id
    ) {
        if (bindingResult.hasErrors()) {
            return "redirect:/users/{id}/form";
        }
        userService.update(id, dto);
        return "redirect:/users";
    }

    @GetMapping("/{id}/form")
    @CheckAuth
    public String userUpdatePage(
            @PathVariable Long id,
            Model model,
            @SessionAttribute(SessionName.AUTH) Auth auth
    ) {
        UserResponseDto user = userService.findById(id);
        if (!user.getId().equals(auth.getId())) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "user/updateForm";
    }
}
