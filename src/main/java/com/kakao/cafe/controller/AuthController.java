package com.kakao.cafe.controller;

import com.kakao.cafe.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
public class AuthController {

    /*
    @PostMapping("/login")
    public ResponseEntity<String> login(HttpSession session){
        User user = (User)session.getAttribute("user");
        try{
            user.validate();
        }catch (IllegalArgumentException e){
            session.invalidate();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("로그아웃");
        }
        return ResponseEntity.status(HttpStatus.OK).body("current user " + user);
    }

    @GetMapping("/login")
    public ResponseEntity<String> isLogin(String userID, String password, HttpSession session){
        User sessionUser = (User)session.getAttribute("user");
        log.debug("userid = {}, password = {}", userID, password);
        session.setAttribute("user", sessionUser);
        return ResponseEntity.status(HttpStatus.OK).body("Current User " + sessionUser);
    }*/



}
