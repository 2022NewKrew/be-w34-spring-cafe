package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.user.UserCreateRequestDto;
import com.kakao.cafe.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;


    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        // 로그인 로직 구현
        logger.info("POST /login: request userId={}, password={}", userId, password);
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
        // 세션 지우기
        session.invalidate();
        return "redirect:/";
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
        Optional<User> user = userService.findUser(userId);
        if (user.isEmpty()) {
            return "error/404";
        }
        logger.info("{}", user.get());
        model.addAttribute("user", user.get());
        return "user/profile";
    }

//    @GetMapping("/users/{userId}/form")
//    public String updateForm(Model model, @PathVariable String userId) {
//        User user = users.stream()
//                .filter(obj -> userId.equals(obj.getUserId()))
//                .findFirst().orElse(null);
//        logger.info("GET /users/{}/form: response user edit page with {}", userId, user);
//        if (user == null) {
//            return "error/404";
//        }
//        // user 수정 페이지 응답
//        model.addAttribute("user", user);
//        return "user/updateForm";
//    }
//
//    @PostMapping("/users/{userId}/update")
//    public String updateUser(User newUser, @PathVariable String userId) {
//        logger.info("POST /users/{}/update: request {} and update", userId, newUser);
//        // user 수정
//        users.stream()
//                .filter(user -> userId.equals(user.getUserId()))
//                .findFirst()
//                .ifPresent(user -> {
//                    user.setEmail(newUser.getEmail());
//                    user.setName(newUser.getName());
//                    user.setPassword(newUser.getPassword());
//                });
//        return "redirect:/users/{userId}";
//    }

}
