package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.dto.ResponseDTO;
import com.kakao.cafe.web.dto.SignUpDTO;
import com.kakao.cafe.web.service.UserService;
import com.kakao.cafe.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/api/user")
  public ResponseEntity<ResponseDTO> createUser(@RequestBody SignUpDTO signUpDTO) {
    userService.createUser(User.of(signUpDTO));
    return ResponseEntity.status(HttpStatus.FOUND)
        .header(HttpHeaders.LOCATION, "/users")
        .body(ResponseDTO.createSuccess());
  }



}
