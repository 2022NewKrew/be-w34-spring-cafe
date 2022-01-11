package com.kakao.cafe.user.presentation;

import com.kakao.cafe.user.application.JoinService;
import com.kakao.cafe.user.application.UserInfoService;
import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.mapper.UserMapper;
import com.kakao.cafe.user.presentation.dto.JoinRequest;
import com.kakao.cafe.user.presentation.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final JoinService joinService;
    private final UserInfoService userInfoService;
    private final UserMapper userMapper;

    @GetMapping("/join")
    public String joinForm(){
        logger.info("회원가입 시도합니다.");

        return "user/form";
    }

    @PostMapping("")
    public String join(JoinRequest joinRequest, Model model){
        logger.info("회원가입 요청이 시도되었습니다.");
        User user = joinService.save(userMapper.toUser(joinRequest));

        model.addAttribute("user", userMapper.toDto(user));
        return "user/join_success";
    }

    @GetMapping("")
    public String listUsers(Model model){
        List<UserDto> users = userInfoService.getAllUsers()
                .stream()
                .map(userMapper::toDto)
                .collect(toList());

        logger.debug(String.valueOf(users.size()));

        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/{id}")
    public String  getUserInfo(@PathVariable String id, Model model){
        User user = userInfoService.getUser(id);
        UserDto userDto = userMapper.toDto(user);

        model.addAttribute("user", userDto);
        return "user/profile";
    }
}
