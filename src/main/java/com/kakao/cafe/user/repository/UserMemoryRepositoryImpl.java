package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserMemoryRepositoryImpl implements UserRepository {
    private final static AtomicLong idSequence = new AtomicLong();
    private final static HashMap<Long, User> userDB = new HashMap<>();

    @Override
    public Optional<User> find(Long id) {
        return Optional.ofNullable(userDB.get(id));
    }

    @Override
    public ArrayList<User> findAll() {
        return new ArrayList<>(userDB.values());
    }

    @Override
    public Optional<User> find(String stringId) {
        Long dbId = userDB.keySet().stream().filter(key->stringId.equals(userDB.get(key).getStringId())).findAny().orElseGet(()->-1L);
        return Optional.ofNullable(userDB.get(dbId));
    }

    public Long persist(User user) {
        User newUser = User.builder()
                .id(idSequence.get())
                .stringId(user.getStringId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .signUpDate(LocalDateTime.now())
                .build();
        userDB.put(idSequence.get(), newUser);
        return idSequence.getAndIncrement();
    }

    public void updateUserInfo(User user) {
        Long id = userDB.keySet().stream().filter(key -> userDB.get(key).getStringId().equals(user.getStringId())).findAny().orElseThrow(()-> new IllegalArgumentException("존재하지 않는 사용자 입니다."));
        User newUser = User.builder()
                .id(id)
                .stringId(user.getStringId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .signUpDate(userDB.get(id).getSignUpDate())
                .build();
        userDB.put(id, newUser);
    }
}
