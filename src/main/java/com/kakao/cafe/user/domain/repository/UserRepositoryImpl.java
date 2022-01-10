package com.kakao.cafe.user.domain.repository;

import com.kakao.cafe.user.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserRepositoryImpl implements UserRepository{
    private final Map<String, User> idToUser = new TreeMap<>();
    private final List<User> userList = new ArrayList<>();

    @Override
    public Optional<User> getUser(String userId) {
        System.out.println(idToUser.size());

        if(!idToUser.containsKey(userId)){
            return Optional.empty();
        }

        return Optional.of(idToUser.get(userId));
    }

    @Override
    public List<User> getAllUsers() {
        return Collections.unmodifiableList(userList);
    }

    @Override
    public void save(User user) {
        idToUser.put(user.getUserId(), user);
        userList.add(user);
    }
}
