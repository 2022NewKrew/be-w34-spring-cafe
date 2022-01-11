package com.kakao.cafe.interfaces.user;

import com.kakao.cafe.application.UserService;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.interfaces.user.dto.UserMapper;
import com.kakao.cafe.interfaces.user.dto.request.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getAllUser(Model model) {
        List<User> userList = userService.findAllUser();
        List<UserDto> userDtoList = UserMapper.convertUserEntityListToDtoList(userList);
        model.addAttribute("users", userDtoList);
        return "member/list";
    }

    @PostMapping("/join")
    public String joinUser(UserDto userDto) {
        User user = UserMapper.convertUserDtoToEntity(userDto);
        userService.join(user);
        return "redirect:/user/all";
    }

}