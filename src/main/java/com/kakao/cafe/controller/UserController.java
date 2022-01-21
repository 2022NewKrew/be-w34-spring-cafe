package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserFormDto;
import com.kakao.cafe.dto.UserNoPwdDto;
import com.kakao.cafe.mapper.UserMapper;
import com.kakao.cafe.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private static final UserMapper USER_MAPPER = UserMapper.INSTANCE;
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public String postSignup(@ModelAttribute UserFormDto userFormDto) {
        final User user = USER_MAPPER.convertToEntity(userFormDto);
        userService.signup(user);
        LOGGER.info("POST request on Signup -> {}", user);
        return "redirect:users";
    }

    @GetMapping()
    public String getUserList(Model model) {
        final List<User> users = userService.getUsers();
        final List<UserNoPwdDto> userNoPwdDtoList = users.stream()
            .map(USER_MAPPER::convertToUserNoPwdDto)
            .collect(Collectors.toList());
        model.addAttribute("users", userNoPwdDtoList);
        return "user/list";
    }

    @GetMapping("/{username}")
    public String getUserProfile(@PathVariable("username") String username, Model model) {
        final User user = userService.getUserByUsername(username);
        final UserNoPwdDto userNoPwdDto = USER_MAPPER.convertToUserNoPwdDto(user);
        model.addAttribute("user", userNoPwdDto);
        return "user/profile";
    }

    @GetMapping("/{username}/form")
    public String getUserUpdate(@PathVariable("username") String username, Model model) {
        final User user = userService.getUserByUsername(username);
        final UserNoPwdDto userNoPwdDto = USER_MAPPER.convertToUserNoPwdDto(user);
        model.addAttribute("user", userNoPwdDto);
        return "user/updateForm";
    }

    @PostMapping("/{username}/form")
    public String postUserUpdate(@PathVariable("username") String username, @ModelAttribute UserFormDto userFormDto) {
        final User user = USER_MAPPER.convertToEntity(userFormDto);
        final User updatedUserDto = userService.updateUser(username, user);
        LOGGER.info("POST request on UpdateUser -> {}", updatedUserDto);
        return "redirect:/users/" + user.getUsername();
    }
}
