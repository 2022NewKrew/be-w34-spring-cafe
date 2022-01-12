package com.kakao.cafe.repository;

import com.kakao.cafe.db.JakeDB;
import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JakeDB jakeDB;

    @Autowired
    public UserRepository(JakeDB jakeDB) {
        this.jakeDB = jakeDB;
    }

    public void save(User user) {
        jakeDB.getUserList().add(user);
    }

    public List<User> findAll() {
        return jakeDB.getUserList();
    }
}
