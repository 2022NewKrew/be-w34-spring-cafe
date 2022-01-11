package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    public String getUsers() {
        log.info("getUsers");
        User user = new User("id", "password", "nugunga", "nugunga@daum.net");
        log.info(user.toString());
        log.info("user list.html");

        return "redirect:user/list.html";
    }
}
