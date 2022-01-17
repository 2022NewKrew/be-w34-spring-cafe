package com.kakao.cafe.controller;

import com.kakao.cafe.constants.Constants;
import com.kakao.cafe.controller.dto.request.UserLoginRequestDto;
import com.kakao.cafe.controller.dto.request.UserSignUpRequestDto;
import com.kakao.cafe.controller.dto.request.UserUpdateRequestDto;
import com.kakao.cafe.controller.dto.response.UserProfileResponseDto;
import com.kakao.cafe.controller.dto.response.UserQueryResponseDto;
import com.kakao.cafe.controller.dto.session.UserLoginSession;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.service.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
            throw new IllegalArgumentException();
        }

        userService.signUp(userSignUpRequestDto);
        return "redirect:/users";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute UserLoginRequestDto userLoginRequestDto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException();
        }

        User loginUser = userService.login(userLoginRequestDto);
        HttpSession session = request.getSession();
        session.setAttribute(Constants.loginUser, new UserLoginSession(loginUser));
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        session.invalidate();

        return "redirect:/";
    }

    @GetMapping("/{userId}/updateForm")
    public String getUpdateForm(@PathVariable String userId, Model model,
                                @SessionAttribute(name = Constants.loginUser) UserLoginSession userLoginSession) {

        validateUserAuthority(userLoginSession.getUserId(), userId);

        User foundUser = userService.findUserByUserId(userId);
        model.addAttribute("userId", foundUser.getUserId());
        return "user/updateForm";
    }

    @PutMapping("/{userId}")
    public String update(@PathVariable String userId, @Validated UserUpdateRequestDto userUpdateRequestDto, BindingResult bindingResult,
                         @SessionAttribute(name = Constants.loginUser) UserLoginSession userLoginSession) {

        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException();
        }

        validateUserAuthority(userLoginSession.getUserId(), userId);

        userService.update(new UserUpdateDto(userId, userUpdateRequestDto));
        return "redirect:/users";
    }

    private void validateUserAuthority(String loginUserId, String ownerUserId) {
        if (!loginUserId.equals(ownerUserId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "권한이 없습니다", new IllegalArgumentException());
        }
    }
}


