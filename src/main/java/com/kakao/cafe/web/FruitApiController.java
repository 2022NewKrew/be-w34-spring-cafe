package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class FruitApiController {

    Logger logger = LoggerFactory.getLogger(FruitApiController.class);

    @PostMapping("/api/login")
    public ResponseEntity<String> login(Fruit fruit, HttpSession session) {
        logger.info("POST /api/login: {}", fruit);
        try {
            fruit.validate();
        } catch (IllegalArgumentException e) {
            logger.info("로그아웃");
            session.invalidate();
            return new ResponseEntity<>("로그인 실패", HttpStatus.UNAUTHORIZED);
        }        session.setAttribute("user", fruit);
        return ResponseEntity.status(HttpStatus.OK).body("login success: " + fruit);
    }

    @GetMapping("/api/login")
    public String isLogin(HttpSession session) {
        Fruit sessionFruit = (Fruit) session.getAttribute("user");
        if (sessionFruit == null) {
            return "현재 세션이 없습니다.";
        }

        logger.info("GET /api/login: {}", sessionFruit);
        return "Current User: " + sessionFruit;
    }
}
