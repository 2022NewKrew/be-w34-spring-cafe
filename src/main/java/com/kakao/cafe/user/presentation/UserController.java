package com.kakao.cafe.user.presentation;

import com.kakao.cafe.user.application.JoinService;
import com.kakao.cafe.user.application.UserInfoService;
import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.presentation.dto.JoinRequest;
import com.kakao.cafe.user.presentation.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final JoinService joinService;
    private final UserInfoService userInfoService;

    @GetMapping("/join")
    public String joinForm(){
        logger.info("회원가입 시도합니다.");

        return "users/form";
    }

    @PostMapping("")
    public String join(JoinRequest joinRequest, Model model){
        logger.info("회원가입 요청이 시도되었습니다.");
        User user = joinService.createUser(joinRequest.getUserId(), joinRequest.getPassword()
                , joinRequest.getName(), joinRequest.getEmail());

        model.addAttribute("user", UserDto.of(user));
        return "users/join_success";
    }

    @GetMapping("")
    public String listUsers(Model model){
        List<UserDto> users = userInfoService.getAllUsers()
                .stream()
                .map(UserDto::of)
                .collect(toList());

        logger.debug(String.valueOf(users.size()));

        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/{id}")
    public String  getUserInfo(@PathVariable String id, Model model){
        User user = userInfoService.getUser(id);
        UserDto userDto = UserDto.of(user);

        model.addAttribute("user", userDto);
        return "users/profile";
    }
}
