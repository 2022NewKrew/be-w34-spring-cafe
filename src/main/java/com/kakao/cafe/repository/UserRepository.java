package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.repository.dao.UserDAO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final UserDAO userDAO;

    public UserRepository(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void create(User user) {
        userDAO.create(user);
    }

    public List<User> getUserList() {
        return userDAO.findAll();
    }

    public Optional<User> findByUserId(String userId) {
        return userDAO.findByUserId(userId);
    }
}
