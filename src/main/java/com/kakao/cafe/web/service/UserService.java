package com.kakao.cafe.web.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.Users;
import com.kakao.cafe.exception.NoEmailFoundException;
import com.kakao.cafe.utils.SessionUtils;
import com.kakao.cafe.web.dto.LoginDTO;
import com.kakao.cafe.web.dto.SignUpDTO;
import com.kakao.cafe.web.dto.UserModifyDTO;
import com.kakao.cafe.web.repository.UserRepository;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private static Logger logger = LoggerFactory.getLogger(UserService.class);
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  /**
   * 패스워드 해시 후 신규 유저 생성 및 반환
   *
   * @param signInfo 신규 유저 요청
   * @return 신규 유저
   */
  public User createUser(SignUpDTO signInfo) {

    User user = User.create(signInfo);
    user.setPasswordHashed();

    return userRepository.save(user);
  }


  /**
   * 모든 유저 반환
   *
   * @return 모든 유저
   */
  public Users findAllUsers() {
    return userRepository.findAll();
  }


  /**
   * id 바탕으로 유저 조회 후 반환
   *
   * @param id 유저 id
   * @return 조회된 유저
   * @throws NoEmailFoundException 조회된 유저 없음
   */
  public User findUserById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(NoEmailFoundException::new);
  }


  /**
   * 패스워드 검증 및 유저 정보 변경 및 세션 업데이트 후 변경된 유저정보 반환
   *
   * @param modifyInfo 유저 정보 변경 요청
   * @return 변경된 유저
   */
  public User modifyUserInformation(UserModifyDTO modifyInfo) {

    User user = userRepository.findById(modifyInfo.getId())
        .orElseThrow(NoEmailFoundException::new);

    user.checkPassword(modifyInfo.getPassword());

    User updateUser = User.create(user, modifyInfo);
    userRepository.save(updateUser);

    SessionUtils.updateUser(updateUser);

    return updateUser;
  }


  /**
   * 로그인, 유저의 인증정보를 검증 하고 마지막 로그인 갱신 및 세션 처리
   *
   * @param loginDTO 로그인 요청
   * @return 로그인 유저
   */
  public User login(LoginDTO loginDTO) {

    User findUser = userRepository.findByEmail(loginDTO.getEmail())
        .orElseThrow(NoEmailFoundException::new);

    findUser.checkPassword(loginDTO.getPassword());
    findUser.updateLastLoginAt();

    userRepository.save(findUser);
    SessionUtils.login(findUser);

    return findUser;
  }


  /**
   * 해당 유저 정보를 수정 권한 유무를 반환, 현재는 로그인한 사용자와 일치할 경우만 처리
   *
   * @param user 수정할 유저
   * @return 수정 권한 유무
   */
  public boolean hasEditPermission(User user) {
    return SessionUtils.isLoginUser(user);
  }


  /**
   * 로그아웃 처리
   */
  public void logout() {
    SessionUtils.logout();
  }

}
