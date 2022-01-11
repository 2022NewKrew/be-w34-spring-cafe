package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private static final String DUPLICATED_EMAIL = "이미 존재하는 이메일입니다.";

    private static final List<User> users = new ArrayList<>();
    static {
        users.add(new User("aa@aa.com","aaa","aaaa"));
    }


    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        checkDuplicated(user);
        users.add(user);
        return user;
    }

    private void checkDuplicated(User user) {
        if(users.contains(user)){
            throw new IllegalArgumentException(DUPLICATED_EMAIL);
        }
    }
}
