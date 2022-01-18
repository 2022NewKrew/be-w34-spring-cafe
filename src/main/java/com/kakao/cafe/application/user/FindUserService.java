package com.kakao.cafe.application.user;

import com.kakao.cafe.application.user.validation.NonExistsUserIdException;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserDaoPort;

import java.util.List;
import java.util.Optional;

public class FindUserService {
    private final UserDaoPort userDao;

    public FindUserService(UserDaoPort userDao) {
        this.userDao = userDao;
    }

    public User findByUserId(String userId) throws NonExistsUserIdException {
        return userDao.findByUserId(userId)
                .orElseThrow(() -> new NonExistsUserIdException());
    }

    public List<User> findAllUser() {
        return userDao.findAll();
    }

    public boolean checkPassWordMatch(String userId, String password) {
        Optional<User> optionalUser = userDao.findByUserIdAndPassword(userId, password);
        return optionalUser.isPresent();
    }
}
