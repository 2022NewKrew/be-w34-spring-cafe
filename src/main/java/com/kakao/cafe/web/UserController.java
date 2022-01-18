package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private DbRepository dbRepository;

    @PostMapping("/users")
    public String create(User user) {
        logger.info("POST /users {}", user);
        try {
            user.validate();
        } catch (IllegalArgumentException e) {
            logger.info("회원가입 실패: {}", user);
            return "redirect:/user/register.html?fail=register";
        }

        dbRepository.saveUser(user);
        return "redirect:/";
    }
}
