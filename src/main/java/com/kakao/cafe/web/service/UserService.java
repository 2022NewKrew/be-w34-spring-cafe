package com.kakao.cafe.web.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.Users;
import com.kakao.cafe.exception.InvalidAuthenticationException;
import com.kakao.cafe.exception.NoEmailFoundException;
import com.kakao.cafe.utils.SessionUtils;
import com.kakao.cafe.web.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
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

  public User createUser(User user) {

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

  public User login(User user) {

    User loginUser = userRepository.findByEmail(user.getEmail())
        .stream()
        .filter(findUser -> BCrypt.checkpw(
            user.getPassword(),
            findUser.getPassword())
        )
        .findAny()
        .orElseThrow(InvalidAuthenticationException::new);

    loginUser.updateLastLoginAt();
    userRepository.updateLoginTime(loginUser);

    SessionUtils.login(loginUser);

    return loginUser;
  }

  public boolean isLoginUser(User user) {
    return SessionUtils.getLoginUser()
        .stream()
        .anyMatch(loginUser -> loginUser.equals(user));
  }

  public void logout() {
    SessionUtils.logout();
  }

}
