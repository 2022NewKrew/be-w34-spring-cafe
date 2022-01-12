package com.kakao.cafe.application;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserPort;
import com.kakao.cafe.domain.user.UserVo;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserPort userPort;

    public UserService(UserPort userPort) {
        this.userPort = userPort;
    }

    public User findByUserId(String userId) throws IllegalArgumentException {
        Optional<User> optionalUser = userPort.findByUserId(userId);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return optionalUser.get();
    }

    public List<User> findAllUser() {
        return userPort.findAll();
    }

    public void join(UserVo userVo) throws IllegalArgumentException {
        Optional<User> optionalUser = userPort.findByUserId(userVo.getUserId());
        if (optionalUser.isPresent()) {
            throw new IllegalArgumentException();
        }

        userPort.save(userVo.convertVoToEntity());
    }

}
