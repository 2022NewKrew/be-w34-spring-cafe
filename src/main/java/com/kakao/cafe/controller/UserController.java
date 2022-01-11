package com.kakao.cafe.controller;

import com.kakao.cafe.user.User;
import com.kakao.cafe.user.UserService;
import com.kakao.cafe.user.dto.UserDto;
import com.kakao.cafe.user.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping(value = "/create")
    public String insertUser(User user) {

        Long id = userService.save(user);

        return "redirect:/";
    }

    @GetMapping
    public String viewUserList(Model model) {
        List<User> users = userService.findAll();

        model.addAttribute("users", users);

        return "user/list";
    }

    @GetMapping("/create")
    public String viewUserCreateForm() {
        return "user/form";
    }

    @GetMapping("/{id}/update")
    public String viewUserUpdateForm(@PathVariable("id") Long id, Model model) {

        User user = userService.findOne(id);
        UserDto userDto = modelMapper.map(user, UserDto.class);

        model.addAttribute("user", userDto);

        return "user/update_form";
    }

    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") @Valid UserUpdateDto userUpdateDto, Model model) {

        User user = userService.findOne(id);
        user.setUserId(userUpdateDto.getUserId());
        user.setEmail(userUpdateDto.getEmail());
        user.setName(userUpdateDto.getName());

        userService.update(user);

        return "redirect:/";
    }

}
