package com.kakao.cafe.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/form")
    public String createUserForm() {
        return "user/form";
    }

    @GetMapping(path = "/{id}/form")
    public String updateUserForm(@PathVariable String id, Model model) {
        model.addAttribute("user", new UserDto(userService.getUserById(id)));
        return "user/updateForm";
    }

    @GetMapping(path = "/list")
    public String userList(Model model) {
        List<UserDto> users = userService.getAllUsers().stream().map(UserDto::new).collect(Collectors.toList());
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping(path = "/login")
    public String userLogin() {
        System.out.println("login");
        return "user/login";
    }

    @GetMapping(path = "/login_failed")
    public String userLoginFailed() {
        System.out.println("login_failed");
        return "user/login_failed";
    }

    @GetMapping(path = "/profile")
    public String userProfile() {
        return "user/profile";
    }

    @GetMapping(path = "/profile/{userId}")
    public String userProfile(@PathVariable String userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", new UserDto(user));
        return "user/profile";
    }

    @PostMapping(path = "/create")
    public String createUser(UserRequest userRequest) {
        userService.createUser(User.builder()
                .id(userRequest.getUserId())
                .password(userRequest.getPassword())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .build());
        return "redirect:/user/list";
    }

    @PutMapping(path = "/update")
    public String updateUser(UserRequest userRequest) {
        if(userService.validateUser(
                User.builder().id(userRequest.getUserId()).password(userRequest.getPassword()).build())) {
            userService.updateUser(User.builder()
                    .id(userRequest.getUserId())
                    .password(userRequest.getPassword())
                    .name(userRequest.getName())
                    .email(userRequest.getEmail())
                    .build());
        }
        return "redirect:/user/list";
    }

}
