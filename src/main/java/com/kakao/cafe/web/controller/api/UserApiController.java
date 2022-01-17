package com.kakao.cafe.web.controller.api;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.web.common.EnableSession;
import com.kakao.cafe.web.controller.KakaoCafeApiController;
import com.kakao.cafe.web.dto.LoginDTO;
import com.kakao.cafe.web.dto.ResponseDTO;
import com.kakao.cafe.web.dto.SignUpDTO;
import com.kakao.cafe.web.dto.UserDTO;
import com.kakao.cafe.web.dto.UserModifyDTO;
import com.kakao.cafe.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@KakaoCafeApiController
@EnableSession
public class UserApiController {

  private static final Logger logger = LoggerFactory.getLogger(UserApiController.class);
  private final UserService userService;

  public UserApiController(UserService userService) {
    this.userService = userService;
  }


  @PostMapping("/user")
  public ResponseEntity<UserDTO> createUser(@RequestBody SignUpDTO signUpDTO) {

    User createdUser = userService.createUser(signUpDTO);

    return ResponseEntity.status(HttpStatus.FOUND)
        .header(HttpHeaders.LOCATION, "/users")
        .body(new UserDTO(createdUser));
  }


  @PutMapping("/user")
  public ResponseEntity<UserDTO> updateUser(@RequestBody UserModifyDTO userModifyDTO) {

    User updateUser = userService.modifyUserInformation(userModifyDTO);

    return ResponseEntity.status(HttpStatus.OK)
        .body(new UserDTO(updateUser));
  }


  @PostMapping("/login")
  public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO) {

    userService.login(loginDTO);

    return ResponseEntity.status(HttpStatus.FOUND)
        .header(HttpHeaders.LOCATION, "/")
        .body(ResponseDTO.createSuccess());
  }


  @PostMapping("/logout")
  public ResponseEntity<ResponseDTO> logout() {

    userService.logout();

    return ResponseEntity.status(HttpStatus.FOUND)
        .header(HttpHeaders.LOCATION, "/")
        .body(ResponseDTO.createSuccess());
  }

}
