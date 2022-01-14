package com.kakao.cafe.persistence.user.memory;

import com.kakao.cafe.domain.user.FindUserPort;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserDaoPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository
public class FindUserInMemoryAdapter implements FindUserPort {

    private final UserDaoPort userDaoPort;

    public FindUserInMemoryAdapter(UserDaoPort userDaoPort) {
        this.userDaoPort = userDaoPort;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return Optional.ofNullable(userDaoPort.getUsers().get(userId));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userDaoPort.getUsers().values());
    }

    @Override
    public Optional<User> findByUserIdAndPassword(String userId, String password) {
        User userById = userDaoPort.getUsers().get(userId);
        if (userById.getPassword().equals(password) == false) {
            return Optional.empty();
        }

        return Optional.of(userById);
    }
}
