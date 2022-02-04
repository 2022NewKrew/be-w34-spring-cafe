package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DbUserRepository implements UserRepository {
    private final UserDao userDao;

    public DbUserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void create(UserInfo userInfo) {
        userDao.insert(userInfo);
    }

    @Override
    public Optional<UserInfo> read(String id) {
        return userDao.selectById(id);
    }

    @Override
    public List<UserInfo> readAll() {
        return userDao.selectAll();
    }
}
