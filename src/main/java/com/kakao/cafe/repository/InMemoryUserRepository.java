package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.exceptions.DuplicateUserException;
import com.kakao.cafe.exceptions.UserNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final List<User> userList;
    private Logger logger = LoggerFactory.getLogger(InMemoryUserRepository.class);


    public InMemoryUserRepository() {
        this.userList = Collections.synchronizedList(new ArrayList<>());
    }

    private boolean isDuplicate(User findUser) {
        return userList.stream()
                .anyMatch(user -> user.getUserId().equals(findUser.getUserId()));
    }

    @Override
    public User save(User user) {
        logger.info("[InMemory] user save");
        if (isDuplicate(user)) {
            throw new DuplicateUserException("사용자가 이미 존재합니다");
        }
        userList.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        logger.info("[InMemory] user findAll");
        return userList;
    }

    @Override
    public User findByUserId(String userId) {
        logger.info("[InMemory] user findByUserId");
        User findUser = userList.stream()
                .filter(user -> Objects.equals(user.getUserId(), userId))
                .findAny()
                .orElseThrow(() -> new UserNotFoundException("사용자 ID가 없습니다"));

        return findUser;
    }
}
