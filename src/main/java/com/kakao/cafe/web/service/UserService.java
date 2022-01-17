package com.kakao.cafe.web.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.Users;
import com.kakao.cafe.exception.NoAuthorityException;
import com.kakao.cafe.exception.NoEmailFoundException;
import com.kakao.cafe.utils.SessionUtils;
import com.kakao.cafe.web.dto.LoginDTO;
import com.kakao.cafe.web.dto.SignUpDTO;
import com.kakao.cafe.web.dto.UserModifyDTO;
import com.kakao.cafe.web.repository.UserRepository;
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



  public User createUser(SignUpDTO signInfo) {

    User user = User.create(signInfo);
    user.setPasswordHashed();

    return userRepository.save(user);
  }



  public Users findAllUsers() {
    return userRepository.findAll();
  }



  public User findUserById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(NoEmailFoundException::new);
  }



  public User modifyUserInformation(UserModifyDTO modifyInfo) {

    User user = userRepository.findById(modifyInfo.getId())
        .orElseThrow(NoEmailFoundException::new);

    user.checkPassword(modifyInfo.getPassword());

    User updateUser = User.create(user, modifyInfo);
    userRepository.save(updateUser);

    SessionUtils.updateUser(updateUser);

    return updateUser;
  }



  public User login(LoginDTO loginDTO) {

    User findUser = userRepository.findByEmail(loginDTO.getEmail())
        .orElseThrow(NoEmailFoundException::new);

    findUser.checkPassword(loginDTO.getPassword());
    findUser.updateLastLoginAt();

    userRepository.save(findUser);
    SessionUtils.login(findUser);

    return findUser;
  }



  public boolean isLoginUser(User user) {
    return SessionUtils.getLoginUser()
        .stream()
        .anyMatch(loginUser -> loginUser.equals(user));
  }



  public void requireLoginUser(User user) {
    if(!isLoginUser(user)) {
      throw new NoAuthorityException();
    }
  }



  public void logout() {
    SessionUtils.logout();
  }

}
