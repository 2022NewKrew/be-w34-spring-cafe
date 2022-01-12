package com.kakao.cafe.web;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.service.user.UserService;
import com.kakao.cafe.web.dto.UserCreateRequest;
import com.kakao.cafe.web.dto.UserListResponse;
import com.kakao.cafe.web.dto.UserProfileResponse;
import com.kakao.cafe.web.dto.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAll().stream()
                .map(UserListResponse::new)
                .collect(Collectors.toList()));
        return "/user/list";
    }

    @PostMapping()
    public String save(@ModelAttribute UserCreateRequest requestDto) {
        userService.save(requestDto.toEntity());
        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    public String showUser(@PathVariable String userId, Model model) {
        model.addAttribute("user", new UserProfileResponse(userService.findById(userId)));
        return "/user/profile";
    }

    @GetMapping("/{userId}/form")
    public String updateForm(@PathVariable String userId, Model model) {
        model.addAttribute("userId", userId);
        UserProfileResponse userProfileResponse = new UserProfileResponse(userService.findById(userId));
        model.addAttribute("name", userProfileResponse.getName());
        model.addAttribute("email", userProfileResponse.getEmail());

        return "/user/updateForm";
    }

    @PostMapping("/{userId}/update")
    public String update(@PathVariable String userId, @ModelAttribute UserUpdateRequest requestDto) {
        User user = userService.findById(userId);
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        userService.update(userId, user);

        return "redirect:/users";
    }

}
