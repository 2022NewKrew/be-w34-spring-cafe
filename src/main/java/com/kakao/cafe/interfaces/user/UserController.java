package com.kakao.cafe.interfaces.user;

import com.kakao.cafe.application.UserService;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.interfaces.user.dto.UserMapper;
import com.kakao.cafe.interfaces.user.dto.request.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getAllUser(Model model) {
        List<User> userList = userService.findAllUser();
        List<UserDto> userDtoList = UserMapper.convertUserEntityListToDtoList(userList);
        model.addAttribute("users", userDtoList);
        return "user/list";
    }

    @GetMapping("{userId}")
    public String getUserByUserId(@PathVariable String userId, Model model) {
        return "user/profile";
    }

    @PostMapping("")
    public String joinUser(UserDto userDto) {
        User user = UserMapper.convertUserDtoToEntity(userDto);
        userService.join(user);
        return "redirect:/users";
    }

}