package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.request.UserLoginRequestDto;
import com.kakao.cafe.controller.dto.response.UserProfileResponseDto;
import com.kakao.cafe.controller.dto.response.UserQueryResponseDto;
import com.kakao.cafe.controller.dto.request.UserSignUpRequestDto;
import com.kakao.cafe.controller.dto.session.UserLoginSession;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller()
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping()
    public String getAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users.stream()
                .map(UserQueryResponseDto::new)
                .collect(Collectors.toUnmodifiableList()));

        return "user/list";
    }

    @GetMapping("/{userId}/profile")
    public String getUserProfile(@PathVariable("userId") String userId, Model model) {
        User foundUser = userService.findUserByUserId(userId);
        UserProfileResponseDto userProfileResponseDto = new UserProfileResponseDto(foundUser);
        model.addAttribute("user", userProfileResponseDto);
        return "user/profile";
    }

    @PostMapping()
    public String signUp(@Validated @ModelAttribute UserSignUpRequestDto userSignUpRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("회원가입 실패: ", IllegalArgumentException.class);
        }

        userService.signUp(userSignUpRequestDto);
        return "redirect:/users";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserLoginRequestDto userLoginRequestDto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.error("로그인 실패: ", IllegalArgumentException.class);
        }

        User loginUser = userService.login(userLoginRequestDto);
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", new UserLoginSession(loginUser));
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        session.invalidate();

        return "redirect:/";
    }
}


