package com.kakao.cafe.user.presentation;

import com.kakao.cafe.user.application.JoinService;
import com.kakao.cafe.user.application.LoginService;
import com.kakao.cafe.user.application.SearchUserService;
import com.kakao.cafe.user.application.UpdateUserInfoService;
import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.entity.UserInfo;
import com.kakao.cafe.user.presentation.dto.JoinRequest;
import com.kakao.cafe.user.presentation.dto.UpdateUserInfoRequest;
import com.kakao.cafe.user.presentation.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final LoginService loginService;
    private final JoinService joinService;
    private final SearchUserService searchUserService;
    private final UpdateUserInfoService updateUserInfoService;

    private final ModelMapper modelMapper;

    @GetMapping("/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession httpSession){
        logger.info("로그인 시도 : user {}", userId);
        loginService.login(userId, password);
        httpSession.setAttribute("userId", userId);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("")
    public String listUsers(Model model){
        List<UserDto> users = searchUserService.getAllUsers()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(toList());

        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/{id}")
    public String  getUserInfo(@PathVariable String id, Model model){
        return "redirect:/";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        logger.info("회원가입 시도합니다.");

        return "user/joinForm";
    }

    @PostMapping("")
    public String join(JoinRequest joinRequest, Model model){
        logger.info("회원가입 요청이 시도되었습니다.");

        User user = modelMapper.map(joinRequest, User.class);
        joinService.save(user);

        model.addAttribute("user", modelMapper.map(user, UserDto.class));
        return "user/join_success";
    }

    @GetMapping("/updateForm")
    public String updateForm(HttpSession httpSession, Model model){
        String userId = Objects.requireNonNull((String) httpSession.getAttribute("userId"), "로그인을 먼저 하세요.");

        model.addAttribute("id", userId);
        return "user/updateForm";
    }

    @PostMapping("/update/{id}")
    public String updateInfo(@PathVariable String id, UpdateUserInfoRequest updateRequest){
        updateUserInfoService.updateUserInfo(id, modelMapper.map(updateRequest, UserInfo.class));
        return "redirect:/users";
    }
}
