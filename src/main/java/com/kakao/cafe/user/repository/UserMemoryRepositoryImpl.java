package com.kakao.cafe.user.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;

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
        Long dbId = userDB.keySet()
                          .stream()
                          .filter(key -> stringId.equals(userDB.get(key).getStringId()))
                          .findAny()
                          .orElseGet(() -> -1L);
        return Optional.ofNullable(userDB.get(dbId));
    }

    @Override
    public Long persist(User user) {
        userDB.put(idSequence.get(), makeUser(idSequence.get(), user, LocalDateTime.now()));
        return idSequence.getAndIncrement();
    }

    @Override
    public void updateUserInfo(User user) {
        Long id = userDB.keySet()
                        .stream()
                        .filter(key -> userDB.get(key).getStringId().equals(user.getStringId()))
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));
        userDB.put(id, makeUser(id, user, user.getSignUpDate()));
    }

    private User makeUser(Long id, User user, LocalDateTime time) {
        User newUser = User.builder()
                           .id(id)
                           .stringId(user.getStringId())
                           .email(user.getEmail())
                           .name(user.getName())
                           .password(user.getPassword())
                           .signUpDate(time)
                           .build();
        return newUser;
    }
}
