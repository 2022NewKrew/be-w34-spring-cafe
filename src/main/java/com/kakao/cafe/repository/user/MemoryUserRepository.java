package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository{

    private static final Map<Long,User> userStore = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public void save(User user) {
        if(user.getId() == null){
            user.setId(++sequence);
        }
        userStore.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userStore.get(id));
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userStore.values().stream()
                .filter(user -> user.getUserId().equals(userId))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userStore.values());
    }

    public void clearUserStore(){
        sequence = 0L;
        userStore.clear();
    }

}
