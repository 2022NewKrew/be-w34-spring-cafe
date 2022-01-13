package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.UserSaveForm;
import com.kakao.cafe.controller.dto.UserResponse;
import com.kakao.cafe.controller.dto.UserJoinForm;
import com.kakao.cafe.controller.dto.UserListDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.util.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Validator validator;

    @PostMapping("/create")
    public String createUser(@ModelAttribute UserJoinForm userDto) {
        try{
            validator.newUserCheck(userDto);
            userService.join(userDto);
            return "redirect:/users";
        } catch (Exception e) {
            return "user/form";
        }
    }

    @GetMapping({"", "/list"})
    public String userList(Model model) {
        List<UserListDto> userList = userService.getUserList();
        model.addAttribute("users", userList);
        return "user/list";
    }

    @GetMapping("/{id}")
    public String userProfile(@PathVariable("id") String userId, Model model) {
        User user = userService.findUser(userId);
        UserResponse dtoUser = UserResponse.from(user);
        model.addAttribute("user", dtoUser);
        return "user/profile";
    }

    @GetMapping("/{id}/update")
    public String showForm(@PathVariable("id") String userId, Model model) {
        User user = userService.findUser(userId);
        UserResponse dtoUser = UserResponse.from(user);
        model.addAttribute("user", dtoUser);
        return "user/updateForm";
    }

    @PutMapping("/{id}")
    public String updateForm(@PathVariable("id") String userId, @ModelAttribute UserSaveForm updateUser) {
        validator.updateUserCheck(updateUser);
        userService.updateUser(userId, updateUser);

        return "redirect:/users";
    }
}
