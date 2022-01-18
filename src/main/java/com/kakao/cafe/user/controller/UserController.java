package com.kakao.cafe.user.controller;


import com.kakao.cafe.user.dto.*;
import com.kakao.cafe.user.factory.UserFactory;
import com.kakao.cafe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/users/create")
    public String singUp(SignUpDTO signUpDTO) {
        userService.signUp(UserFactory.toUser(signUpDTO));
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<UserViewDTO> userList = userService.getAllUsers()
                .stream()
                .map(UserViewDTO::new)
                .collect(Collectors.toList());
        model.addAttribute("users", userList);
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String getUserByUserId(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user", new ProfileViewDTO(userService.findByUserId(userId)));
        return "user/profile";
    }

    @GetMapping("/users/{userId}/form")
    public String getEditUserForm(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user", new UserViewDTO(userService.findByUserId(userId)));
        return "user/updateForm";
    }

    @PutMapping("/users/update")
    public String updateUser(UpdateDTO updateDTO, HttpSession session) throws AccessDeniedException {
        userService.updateUser(updateDTO, getUserIdFromSession(session));
        return "redirect:/users";
    }

    @PostMapping("/users/login")
    public String login(LoginDTO loginDTO, HttpSession session) {
        userService.login(loginDTO.getUserId(), loginDTO.getPassword(), session);
        return "redirect:/";
    }

    @GetMapping("/users/login")
    public String getLoginPage() {
        return "/user/login";
    }

    private String getUserIdFromSession(HttpSession session) {
        return (String) session.getAttribute("sessionOfUser");
    }


}
