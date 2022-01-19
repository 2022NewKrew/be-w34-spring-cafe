package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;

import com.kakao.cafe.domain.user.UserName;
import com.kakao.cafe.domain.user.Users;
import com.kakao.cafe.exception.DuplicateUserException;
import com.kakao.cafe.repository.mapper.InMemoryUserMapper;
import java.util.List;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Users users;
    private final InMemoryUserMapper userMapper;

    public InMemoryUserRepository(InMemoryUserMapper userMapper) {
        this.users = new Users();
        this.userMapper = userMapper;
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
        return users.getUserList().stream()
                .map(userMapper::mapResult)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findUserById(UUID id) {
        return users.findById(id)
                .map(userMapper::mapResult);
    }

    @Override
    public void update(User user) {
        users.update(user);
    }

    @Override
    public Optional<User> findUserByUserNameAndPassword(UserName userName, Password password) {
        return users.findByUserNameAndPassword(userName, password)
                .map(userMapper::mapResult);
    }
}
