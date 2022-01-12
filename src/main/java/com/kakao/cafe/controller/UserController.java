package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping()
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/login")
    public String userLogin() {
        return "user/login";
    }

    @GetMapping("/signup")
    public String userSignUpForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user/signup";
    }

    @PostMapping("/signup")
    public String userSignUp(UserDTO userDTO) {
        userService.join(userDTO);
        return "redirect:/user";
    }

    @GetMapping("/{key}")
    public String userProfile(@PathVariable Long key, Model model) {
        Optional<UserDTO> userDTO = userService.findByKeyDTO(key);
        if (userDTO.isEmpty()) {
            log.info("user doesn't exist");
            return "redirect:/user";
        }
        model.addAttribute("user", userDTO.get());
        return "user/profile";
    }

    @GetMapping("/{key}/update")
    public String userUpdateForm(@PathVariable Long key, Model model) {
        Optional<UserDTO> userDTO = userService.findByKeyDTO(key);
        if (userDTO.isEmpty()) {
            log.info("user doesn't exist");
            return "redirect:/user";
        }
        model.addAttribute("user", userDTO.get());
        return "user/form";
    }

    @PostMapping("/{key}/update")
    public String userUpdate(@PathVariable Long key, UserDTO userDTO) {
        userService.updateByKey(key, userDTO);
        return "redirect:/user";
    }
}
