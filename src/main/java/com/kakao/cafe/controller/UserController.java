package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.exceptions.InvalidLoginRequestException;
import com.kakao.cafe.exceptions.InvalidUserRequestException;
import com.kakao.cafe.request.LoginRequest;
import com.kakao.cafe.request.UserSignupRequest;
import com.kakao.cafe.response.ProfileResponse;
import com.kakao.cafe.response.UserListResponse;
import com.kakao.cafe.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final String SESSION = "sessionUser";
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public String signUp(@Valid UserSignupRequest userDto, BindingResult errors) {
        logger.info("[POST]  회원가입하기");
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining("\n"));
            throw new InvalidUserRequestException(errorMessage);
        }

        User user = userDto.toEntity();
        userService.register(user);

        return "redirect:/users";
    }

    @GetMapping()
    public String userList(Model model) {
        logger.info("[GET] 회원 목록 조회");

        List<UserListResponse> userList = userService.getUserList();
        model.addAttribute("userList", userList);

        return "user/list";
    }

    @GetMapping("/{id}")
    public String userProfile(@PathVariable int id, Model model) {
        logger.info("[GET] /{id} 프로필 조회");

        ProfileResponse profile = userService.getProfile(id);
        model.addAttribute("profile", profile);

        return "user/profile";
    }

    @PostMapping("/login")
    public String login(@Valid LoginRequest request, HttpSession session, BindingResult errors) {
        logger.info("[POST] /login 로그인");
        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining("\n"));

            throw new InvalidLoginRequestException(errorMessage);
        }
        session.setAttribute(SESSION, userService.login(request));
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        logger.info("[GET] /logout 로그아웃");
        session.invalidate();
        return "redirect:/";
    }
}
