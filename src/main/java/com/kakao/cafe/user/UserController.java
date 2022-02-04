package com.kakao.cafe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-10 010
 * Time: 오후 1:25
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String users(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return  "user/list";
    }

    @PostMapping("")
    public String addUser(UserDto userDto) {
        userService.addUser(userDto);
        return "redirect:/user";
    }

    @GetMapping("/{id}")
    public String userProfile(Model model, @PathVariable("id") Integer id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", new UserDto(user));
        return "user/profile";
    }

    @GetMapping("/{id}/form")
    public String updateUserView(Model model, @PathVariable("id") int id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", new UserDto(user));
        return "user/updateForm";
    }

    @PutMapping("/{id}")
    public String updateUser(Model model, UserDto userDto, @PathVariable("id") int id, BindingResult bindingResult) {
        User user = userService.updateUser(userDto, id);
        if (user == null) {
            model.addAttribute("user", userDto);
            bindingResult.addError(new FieldError("user", "password", "match.password"));
            return "user/updateForm";
        }
        return "redirect:/user";
    }
}
