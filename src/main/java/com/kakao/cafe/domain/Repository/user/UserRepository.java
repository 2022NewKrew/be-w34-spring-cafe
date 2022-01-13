package com.kakao.cafe.domain.Repository.user;

import com.kakao.cafe.domain.Entity.User;
import com.kakao.cafe.exceptions.NoSuchUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private static final Map<String, User> users = new HashMap<>();

    public void saveUser(User user) {
        users.put(user.getUserId(), user);
    }

    public boolean isUserIdExist(String userId) {
        return users.containsKey(userId);
    }

    public List<User> findAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User findUserByUserId(String userId) throws NoSuchUserException {
        if (isUserIdExist(userId)) {
            return users.get(userId);
        }
        throw new NoSuchUserException();
    }

    public void updateUser(User user) {
        users.replace(user.getUserId(), user);
    }


}
