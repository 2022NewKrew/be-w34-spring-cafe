package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;

import com.kakao.cafe.domain.user.UserName;
import com.kakao.cafe.domain.user.Users;
import com.kakao.cafe.exception.DuplicateUserException;
import java.util.List;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Users users;

    public InMemoryUserRepository() {
        this.users = new Users();
    }

    @Override
    public synchronized void save(User user) {
        if (users.isUserDuplicated(user)) {
            throw new DuplicateUserException("해당 아이디를 사용중인 사용자가 이미 존재합니다.");
        }
        users.add(user);
    }

    @Override
    public List<User> findAll() {
        return users.getUserList();
    }

    @Override
    public Optional<User> findUserByName(UserName id) {
        return users.findByUserName(id);
    }

    @Override
    public Optional<User> findUserById(UUID id) {
        return users.findById(id);
    }
}
