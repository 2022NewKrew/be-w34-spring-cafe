package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.util.exception.throwable.InvalidPasswordException;
import com.kakao.cafe.util.exception.throwable.UnauthorizedActionException;
import com.kakao.cafe.util.exception.wrappable.LoginFailException;
import com.kakao.cafe.util.exception.wrappable.UserDuplicateException;
import com.kakao.cafe.util.exception.wrappable.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void insert(User user) {
        try {
            userRepository.insert(user);
        } catch (Exception e) {
            throw new UserDuplicateException(e, user.getId());
        }
    }

    public User findById(String id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new UserNotFoundException(e, id);
        }
    }

    public void update(User newInfo, String oldPassword) throws InvalidPasswordException {
        User oldInfo = findById(newInfo.getId());
        if (oldInfo.isPassword(oldPassword))
            throw new InvalidPasswordException(oldPassword);
        userRepository.update(newInfo);
    }

    public String login(String id, String password) {
        LOGGER.info("login() : start");
        User user = null;
        try {
            user = userRepository.findById(id);
        } catch (Exception exception) {
            LOGGER.error("login() : loginFailException id not exists : {}", id);
            throw new LoginFailException(exception, id);
        }
        if (user.isPassword(password)) {
            LOGGER.error("login() : loginFailException password Invalid : (password : {}, user.password : {})", password, user.getPassword());
            throw new LoginFailException(new InvalidPasswordException(password), id);
        }
        LOGGER.info("login() : user.getId() : {}", user.getId());
        return user.getId();
    }

    public void canUpdate(String user1, String user2) {
        if (!user1.equals(user2))
            throw new UnauthorizedActionException(String.format("'%s', '%s'", user1, user2));
    }

    public Users findAll() {
        return userRepository.findAll();
    }
}
