package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private DbRepository dbRepository;


    @GetMapping("/users")
    public String users(HttpSession session) {
        return "user";
    }

    @PostMapping("/users")
    public String create(User user) {
        logger.info("POST /users {}", user);
        try {
            user.validate();
            User newUser = dbRepository.saveUser(user);
            newUser.validId();
            return "redirect:/users";
        } catch (IllegalArgumentException e) {
            logger.info("회원가입 실패: {}", user);
            return "redirect:/user/register.html?fail=register";
        }
    }

    @PostMapping("/login")
    public String login(User user, HttpSession session) {
        User newUser = dbRepository.findUserByEmail(user.getEmail());
        if (newUser == null) {
            logger.info("로그인 실패: {}", user);
            session.invalidate();
            return "redirect:/user/login.html?fail=login";
        }
        logger.info("로그인 성공: {}", newUser);
        session.setAttribute("sessionUser", newUser);
        return "redirect:/users";
    }
}
