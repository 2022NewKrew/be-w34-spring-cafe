package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.exception.InputDataException;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller()
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String form() {
        return "./user/form";
    }

    @PostMapping()
    public String signUp(@Valid UserDto userDto, BindingResult bindingResult) throws InputDataException {
        if (bindingResult.hasErrors()) {
            throw new InputDataException("입력이 올바르지 않습니다.");
        }
        userService.addUser(userDto);
        logger.info("회원 등록 완료 : {}", userDto);
        return "redirect:/users";
    }

    @GetMapping()
    public String users(Model model) {
        List<UserDto> users = userService.getUsers();
        model.addAttribute("users", users);
        return "./user/list";
    }

    @GetMapping("/{userId}")
    public String profile(@PathVariable String userId, Model model) {
        UserDto matchedUser = userService.gerUser(userId);
        model.addAttribute("matchedUser", matchedUser);
        return "./user/profile";
    }

    @GetMapping("/login")
    public String login() {
        return "./user/login";
    }

    @GetMapping("/{userId}/update")
    public String getUpdateUserForm(@PathVariable String userId, Model model) {
        UserDto matchedUser = userService.gerUser(userId);
        model.addAttribute("matchedUser", matchedUser);
        return "./user/updateForm";
    }

    @PutMapping("/{userId}/update")
    public String updateUser(@PathVariable String userId, String curPassword, String newPassword, String name, String email) throws InputDataException {
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setPassword(newPassword);
        userDto.setName(name);
        userDto.setEmail(email);
        userService.updateUser(userDto, curPassword);
        logger.info("회원 수정 완료 : {}",userDto);
        return "redirect:/users";
    }

}
