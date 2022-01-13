package com.kakao.cafe.repository;

import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    private final UserDao userDao;

    @Autowired
    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public void deleteAll() {
        userDao.deleteAll();
    }

    public boolean insert(User user) {
        return userDao.insert(user);
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(userDao.findById(id));
    }


    public Users findAll() {
        return userDao.findAll();
    }

    public boolean update(User userNew) {
        return userDao.update(userNew);
    }
}
