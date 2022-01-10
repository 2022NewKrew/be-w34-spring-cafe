package com.kakao.cafe.domain.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class UserInMemoryRepository implements UserRepository {
    private static final List<User> userList = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Optional<User> findByUserId(String userId) {
        return userList.stream()
                .filter(e -> e.getUserId().equals(userId))
                .findAny();
    }

    @Override
    public void save(User user) {
        userList.add(user);
    }
}
