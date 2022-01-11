package com.kakao.cafe.controller;

import com.kakao.cafe.model.user.UserDto;
import com.kakao.cafe.model.user.UserSignupRequest;
import com.kakao.cafe.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public String signup(@Valid UserSignupRequest user, Errors errors, RedirectAttributes rttr) {
        try {
            validateParams(errors);
            userService.signupUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            rttr.addFlashAttribute("msg", e.getMessage());
            return "redirect:/signup";
        }
        rttr.addFlashAttribute("msg", "회원가입에 성공하였습니다.");
        return "redirect:/users";
    }

    private void validateParams(Errors errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> log.warn("{}", error.getDefaultMessage()));
            throw new IllegalArgumentException("형식에 맞지 않는 입력 값입니다.");
        }
    }

    @GetMapping
    public String getUsers(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("sizeOfUsers", users.size());
        model.addAttribute("users", users);
        return "user/list";
    }

}
