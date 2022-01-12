package com.kakao.cafe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final UserDao userDao;

    @Autowired
    public UserRepository(UserDao userDaoInMemoryMap) {
        this.userDao = userDaoInMemoryMap;
    }

    public void add(User user) {
        userDao.create(user);
    }

    public List<User> getAll() {
        return userDao.readAll();
    }

    public Optional<User> get(String username) {
        return userDao.read(username);
    }

    public Optional<User> get(Long id) {
        return userDao.read(id);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void remove(User user) {
        userDao.delete(user.getId());
    }

    public void remove(Long id) {
        userDao.delete(id);
    }
}
