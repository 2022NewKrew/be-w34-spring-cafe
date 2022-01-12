package com.kakao.cafe.controller.user;

import com.kakao.cafe.model.User;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("users", userToUserElementDto(userService.getUsers()));
        return "user/list";
    }

    @PostMapping
    public String register(UserRegisterDto userRegisterDto) {
        userService.createUser(
                userRegisterDto.getUserId(),
                userRegisterDto.getPassword(),
                userRegisterDto.getName(),
                userRegisterDto.getEmail()
        );
        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    public String showUserProfile(@PathVariable String userId, Model model) {
        model.addAttribute("user", new UserInformationDto(userService.findUserByUserId(userId)));
        return "user/profile";
    }

    @GetMapping("/{userId}/update")
    public String showUpdateUserInformation(@PathVariable String userId, Model model) {
        model.addAttribute("user", new UserInformationDto(userService.findUserByUserId(userId)));
        return "user/updateForm";
    }

    @PostMapping("/{userId}/update")
    public String updateUserInformation(@PathVariable String userId, UserUpdateDto userUpdateDto) {
        userService.updateUser(
                userId,
                userUpdateDto.getPassword(),
                userUpdateDto.getName(),
                userUpdateDto.getEmail()
        );
        return "redirect:/users";
    }

    private List<UserElementDto> userToUserElementDto(List<User> users) {
        return IntStream
                .range(0, users.size())
                .mapToObj(index -> new UserElementDto(users.get(index), index + 1))
                .collect(Collectors.toList());
    }
}
