package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryUserRepository implements UserRepository {

    private static List<User> store;
    private static long sequence;

    public MemoryUserRepository() {
        store = new ArrayList<>();
        sequence = 0L;
    }

    @Override
    public User save(User user) {
        //이미 존재하는 유저일 경우 업데이트 수행
        if (findByEmail(user.getEmail()).isPresent()) {
            return update(user);
        }

        user.setId(sequence++);
        store.add(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return store.stream()
                .filter(user -> user.getId() == id)
                .findAny();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return store.stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny();
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return store.stream()
                .filter(user -> user.getNickname().equals(nickname))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return store;
    }

    public void clearStore() {
        store.clear();
    }

    private User update(User user) {
        int index = -1;
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getId() == user.getId()) {
                index = i;
                break;
            }
        }
        return store.set(index, user);
    }
}
