package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.user.UserCreateRequestDto;
import com.kakao.cafe.web.exception.UnauthorizedException;
import com.kakao.cafe.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        logger.info("POST /login: request userId={}, password={}", userId, password);

        // 로그인 로직 구현
        // userId, password 검사해서 실제 DB에 저장된 사용자인지 체크
        try {
            User sessionUser = userService.authenticate(userId, password);
            session.setAttribute("sessionUser", sessionUser);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            return "redirect:/user/loginForm";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        logger.info("GET /logout: request logout");

        // 세션 지우기
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/users")
    public String createUser(UserCreateRequestDto userCreateRequestDto) {
        logger.info("POST /users: request {}", userCreateRequestDto);

        // user 생성
        User user = new User();
        user.setUserId(userCreateRequestDto.getUserId());
        user.setEmail(userCreateRequestDto.getEmail());
        user.setName(userCreateRequestDto.getName());
        user.setPassword(userCreateRequestDto.getPassword());
        userService.join(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        logger.info("GET /users: response user list page");

        // users 조회
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String showUser(Model model, @PathVariable String userId) {
        logger.info("GET /users/{}: response user profile page", userId);

        // user 조회
        User user = userService.findUser(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/users/{userId}/form")
    public String updateForm(Model model, @PathVariable String userId, @SessionAttribute("sessionUser") User sessionUser) {

        if (!userId.equals(sessionUser.getUserId())) {
            throw new UnauthorizedException("정보를 수정할 권한이 없습니다.");
        }

        // userId 로 user 찾기
        User user = userService.findUser(userId);
        // user 가 있으면 수정 페이지 응답
        logger.info("GET /users/{}/form: response user edit page with {}", userId, user);
        model.addAttribute("user", user);
        return "user/updateForm";
    }

    @PutMapping("/users/{userId}/update")
    public String updateUser(User newUser, @PathVariable String userId, @SessionAttribute("sessionUser") User sessionUser) {

        if (!userId.equals(sessionUser.getUserId())) {
            throw new UnauthorizedException("정보를 수정할 권한이 없습니다.");
        }

        logger.info("PUT /users/{}/update: request {} and update", userId, newUser);
        // user 수정
        userService.update(newUser, userId);
        return "redirect:/users/{userId}";
    }

}
