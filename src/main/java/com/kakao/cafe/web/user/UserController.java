package com.kakao.cafe.web.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserId;
import com.kakao.cafe.service.user.UserCreateService;
import com.kakao.cafe.service.user.UserFindService;
import com.kakao.cafe.service.user.UserUpdateService;
import com.kakao.cafe.web.user.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserCreateService userCreateService;
    private final UserFindService userFindService;
    private final UserUpdateService userUpdateService;

    @Autowired
    public UserController(UserCreateService userCreateService, UserFindService userFindService, UserUpdateService userUpdateService) {
        this.userCreateService = userCreateService;
        this.userFindService = userFindService;
        this.userUpdateService = userUpdateService;
    }

    @GetMapping()
    public String showUsers(Model model) {
        UserListResponse userListResponse = new UserListResponse(
                userFindService.findAll().stream()
                        .map(UserResponse::new)
                        .collect(Collectors.toList())
        );

        model.addAttribute("users_length", userListResponse.getUsers().size());
        model.addAttribute("users", userListResponse);
        return "/user/list";
    }

    @PostMapping()
    public String save(@RequestParam String userId,
                       @RequestParam String password,
                       @RequestParam String name,
                       @RequestParam String email,
                       Model model
    ) {
        UserCreateRequest requestDto = new UserCreateRequest(userId, password, name, email);
        User user = requestDto.toEntity();

        userCreateService.create(user);

        model.addAttribute("user", user);
        return "/user/signup_success";
    }

    @GetMapping("/{userId}")
    public String showUser(@PathVariable UserId userId, Model model) {
        model.addAttribute("user", new UserProfileResponse(userFindService.findById(userId)));
        return "/user/profile";
    }

    @GetMapping("/{userId}/form")
    public String updateForm(@PathVariable UserId userId, Model model) {
        model.addAttribute("userId", userId);
        UserProfileResponse userProfileResponse = new UserProfileResponse(userFindService.findById(userId));
        model.addAttribute("name", userProfileResponse.getName());
        model.addAttribute("email", userProfileResponse.getEmail());

        return "/user/updateForm";
    }

    @PutMapping("/{userId}/update")
    public String update(@PathVariable UserId userId, @ModelAttribute UserUpdateRequest requestDto) {
        User user = userFindService.findById(userId);
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());

        userUpdateService.update(user, requestDto.getPassword());

        return "redirect:/users";
    }

}
