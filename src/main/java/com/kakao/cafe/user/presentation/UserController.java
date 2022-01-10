package com.kakao.cafe.user.presentation;

import com.kakao.cafe.user.application.JoinService;
import com.kakao.cafe.user.application.UserInfoService;
import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.presentation.dto.JoinRequest;
import com.kakao.cafe.user.presentation.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController()
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final JoinService joinService;
    private final UserInfoService userInfoService;

    @GetMapping("/join")
    public ModelAndView joinForm(){
        logger.info("회원가입 시도합니다.");

        return new ModelAndView("user/form");
    }

    @GetMapping("/create")
    public ModelAndView join(JoinRequest joinRequest){
        logger.info("회원가입 요청이 시도되었습니다.");
        joinService.createUser(joinRequest.getUserId(), joinRequest.getPassword()
                , joinRequest.getName(), joinRequest.getEmail());

        return new ModelAndView("redirect:");
    }

    @GetMapping("")
    public ModelAndView listUsers(){
        List<UserDto> users = userInfoService.getAllUsers()
                .stream()
                .map(UserDto::of)
                .collect(toList());

        logger.debug(String.valueOf(users.size()));

        ModelAndView modelAndView = new ModelAndView("user/list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getUserInfo(@PathVariable String id){
        User user = userInfoService.getUser(id);
        UserDto userDto = UserDto.of(user);

        ModelAndView modelAndView = new ModelAndView("user/profile");
        modelAndView.addObject("user", userDto);
        return modelAndView;
    }
}
