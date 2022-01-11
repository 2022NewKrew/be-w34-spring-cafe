package com.kakao.cafe.domain.Repository.user;

import com.kakao.cafe.domain.Entity.User;
import com.kakao.cafe.exceptions.UserNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private static final List<User> users = new ArrayList<>();

    public void saveUser(User user) {
        users.add(user);
    }

    public List<User> findAllUsers() {
        return users;
    }

    public User findUserByUserId(String userId) throws UserNotExistException {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        throw new UserNotExistException();
    }

}
