package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.CheckAuth;
import com.kakao.cafe.domain.auth.Auth;
import com.kakao.cafe.domain.user.UserSaveDto;
import com.kakao.cafe.exception.ForbiddenException;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.utils.SessionName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
            BindingResult bindingResult,
            Model model
    ) {
        if (!Utils.isValidBindingResult(bindingResult, model)) {
            return "user/form";
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
            @PathVariable Long id,
            @Valid @ModelAttribute UserSaveDto dto,
            BindingResult bindingResult,
            Model model
    ) {
        if (!Utils.isValidBindingResult(bindingResult, model)) {
            model.addAttribute("userId", id);
            model.addAttribute("user", dto);
            return "user/updateForm";
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
        if (!id.equals(auth.getId())) {
            throw new ForbiddenException("수정할 유저와 로그인한 유저가 다릅니다.");
        }
        model.addAttribute("userId", id);
        model.addAttribute("user", userService.findById(id));
        return "user/updateForm";
    }
}
