package com.kakao.cafe.domain;

import com.kakao.cafe.exceptions.DuplicateUserException;
import com.kakao.cafe.exceptions.UserNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final List<User> userList;

    public InMemoryUserRepository() {
        this.userList = Collections.synchronizedList(new ArrayList<>());
    }

    private boolean isDuplicate(User findUser) {
        return userList.stream().anyMatch(user -> user.getUserId().equals(findUser.getUserId()));
    }

    public User save(User user) {
        if (isDuplicate(user)) {
            throw new DuplicateUserException("사용자가 이미 존재합니다");
        }
        userList.add(user);
        return user;
    }

    public List<User> findAll() {
        return userList;
    }

    public User findByUserId(String id) {
        User findUser = userList.stream().filter(user -> Objects.equals(user.getUserId(), id)).findAny()
                .orElseThrow(() -> new UserNotFoundException("사용자 ID가 없습니다"));

        return findUser;
    }
}
