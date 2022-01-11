package com.kakao.cafe.web.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.Users;
import com.kakao.cafe.exception.NoEmailFoundException;
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

  public User createUser(User user) {
    user.setPasswordEncrypted();
    return userRepository.save(user);
  }

  public Users getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserByEmail(String email) {
    String decodedEmail = URLDecoder.decode(email, StandardCharsets.UTF_8);
    return userRepository.findByEmail(decodedEmail)
        .orElseThrow(NoEmailFoundException::new);
  }

}
