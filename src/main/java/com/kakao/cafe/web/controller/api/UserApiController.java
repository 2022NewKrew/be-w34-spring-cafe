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
import javax.servlet.http.HttpServletRequest;
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


  /**
   * 신규 유저 생성 후 결과 반환
   *
   * @param signUpDTO 가입 요청
   * @return 신규 유저
   */
  @PostMapping("/user")
  public ResponseEntity<UserDTO> createUser(@RequestBody SignUpDTO signUpDTO) {

    User createdUser = userService.createUser(signUpDTO);

    return ResponseEntity.status(HttpStatus.FOUND)
        .header(HttpHeaders.LOCATION, "/users")
        .body(new UserDTO(createdUser));
  }


  /**
   * 유저 프로필 정보 수정 후 결과 반환
   *
   * @param userModifyDTO 프로필 수정 요청
   * @return 수정된 유저
   */
  @PutMapping("/user")
  public ResponseEntity<UserDTO> updateUser(@RequestBody UserModifyDTO userModifyDTO) {

    User updateUser = userService.modifyUserInformation(userModifyDTO);

    return ResponseEntity.status(HttpStatus.OK)
        .body(new UserDTO(updateUser));
  }


  /**
   * 로그인 후 결과 반환, Referer 를 기준으로 리다이렉트 한다.
   *
   * @param loginDTO 로그인 요청
   * @return redirect to index page
   */
  @PostMapping("/login")
  public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {

    userService.login(loginDTO);

    String referer = request.getHeader(HttpHeaders.REFERER);

    return ResponseEntity.status(HttpStatus.FOUND)
        .header(HttpHeaders.LOCATION, referer)
        .body(ResponseDTO.createSuccess());
  }


  /**
   * 로그아웃 처리
   *
   * @return redirect to index page
   */
  @PostMapping("/logout")
  public ResponseEntity<ResponseDTO> logout() {

    userService.logout();

    return ResponseEntity.status(HttpStatus.FOUND)
        .header(HttpHeaders.LOCATION, "/")
        .body(ResponseDTO.createSuccess());
  }

}
