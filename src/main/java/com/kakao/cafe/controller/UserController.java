package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String signUp(User user) {
        logger.info("[POST] /create 회원가입하기");
        logger.info("사용자 정보] 아이디 {}, 이름 {}", user.getUserId(), user.getName());

        userService.insertUser(user);

        return "redirect:/users";
    }

    @GetMapping()
    public String userList(Model model) {
        logger.info("[GET] 회원 목록 조회");

        List<User> userList = userService.getUserList();
        model.addAttribute("userList", userList);

        return "user/list";
    }

    @GetMapping("/form")
    String form() {
        logger.info("[GET] /form");
        return "user/form";
    }

    @GetMapping("/{userId}")
    public String userProfile(@PathVariable String userId, Model model) {
        logger.info("[GET] /{userId} 프로필 조회");

        User user = userService.getUserById(userId);
        logger.info("사용자 정보] 아이디 {}, 이름 {}", user.getUserId(), user.getName());
        model.addAttribute("user", user);

        return "user/profile";
    }
}
