package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.vo.UserDto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository{

    private static final Map<Long,User> userStore = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public void save(UserDto userDto) {
        User user = new User(++sequence, userDto);
        userStore.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userStore.get(id));
    }

    @Override
    public Optional<User> findByName(String name) {
        return userStore.values().stream()
                .filter(user -> user.getName().equals(name))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userStore.values());
    }
}
