package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryUserRepository implements UserRepository {

    private List<User> users;

    public MemoryUserRepository() {
        users = new ArrayList<>();
        users.add(new User(1,"wldus", 123, "jiyeon", "dhso@kk.kk"));
    }
    
    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        for (User user : users) {
            if (userId.equals(user.getUserId())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public int save(User user) {
        users.add(user);
        return user.getId();
    }

    @Override
    public int update(User user) {
        for (int i=0; i<users.size(); i++) {
            if (users.get(i).getUserId().equals(user.getUserId())) {
                users.set(i, user);
                return users.get(i).getId();
            }
        }
        return 0;
    }

}
