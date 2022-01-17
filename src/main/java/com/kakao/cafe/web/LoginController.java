package com.kakao.cafe.web;

import com.kakao.cafe.domain.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    //이런 느낌으로 하기
    @PostMapping("")
    public ResponseEntity<String> login(User user, HttpSession session) {
        logger.info("POST 주소: {}", user);
        try {
            user.validate(); //db 에서 읽어 오는 것 하기
        } catch (IllegalArgumentException e) {
            logger.info("세션 삭제를 위한 로그아웃");
            session.invalidate();
            return new ResponseEntity<String>("로그아웃", HttpStatus.UNAUTHORIZED);
        }

        session.setAttribute("user", user);
        return ResponseEntity.status(HttpStatus.OK).body("Login Success: " + user);
    }

    @GetMapping("")
    public ResponseEntity<String> isLogin(HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        sessionUser.validate();
        logger.info("GET 주소: {}", sessionUser);
        return ResponseEntity.status(HttpStatus.OK).body("Current User: " + sessionUser);
    }
}
