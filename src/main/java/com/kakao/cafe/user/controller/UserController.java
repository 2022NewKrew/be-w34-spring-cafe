package com.kakao.cafe.user.controller;


import com.kakao.cafe.user.dto.ProfileViewDTO;
import com.kakao.cafe.user.dto.SignUpDTO;
import com.kakao.cafe.user.dto.UpdateDTO;
import com.kakao.cafe.user.dto.UserViewDTO;
import com.kakao.cafe.user.factory.UserFactory;
import com.kakao.cafe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
    public String updateUser(UpdateDTO updateDTO) {
        userService.updateUser(updateDTO);
        return "redirect:/users";
    }


}
