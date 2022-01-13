package com.kakao.cafe.repository;

import com.kakao.cafe.db.JakeDB;
import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO implements UserDAOInterface {
    private final JakeDB jakeDB;

    @Autowired
    public UserDAO(JakeDB jakeDB) {
        this.jakeDB = jakeDB;
    }

    @Override
    public void save(User user) {
        jakeDB.getUserList().add(user);
    }

    @Override
    public List<User> findAll() {
        return jakeDB.getUserList();
    }

    @Override
    public User findById(String userId) {
        return jakeDB.getUserList()
                .stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }
}
