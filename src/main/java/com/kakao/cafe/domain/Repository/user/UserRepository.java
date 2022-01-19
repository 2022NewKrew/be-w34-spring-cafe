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
    private static User user1 = new User("jaden.dev", "123", "허홍준", "jaden.dev@kakaocorp.com");
    private static User user2 = new User("pride.chicken", "456", "심재익", "pride.chicken@kakaocorp.com");
    private static final Map<String, User> users = new HashMap<>() {
        {
            put(user1.getUserId(), user1);
            put(user2.getUserId(), user2);
        }
    };

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

    public void updateUser(String userId, String name, String email) throws NoSuchUserException {
        User targetUser = findUserByUserId(userId);
        targetUser.setName(name);
        targetUser.setEmail(email);
    }


}
