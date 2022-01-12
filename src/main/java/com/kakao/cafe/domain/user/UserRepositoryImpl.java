package com.kakao.cafe.domain.user;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();

    private Long SEQ_NO_OF_USERS = 0L;

    @Override
    public void save(User user) {
        if (ObjectUtils.isEmpty(user)) {
            throw new IllegalArgumentException("사용자의 정보가 없어서 저장할 수 없습니다.");
        }
        if (findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }
        user.setId(++SEQ_NO_OF_USERS);
        users.put(SEQ_NO_OF_USERS, user);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public void update(User user) {
        if (ObjectUtils.isEmpty(user)) {
            throw new IllegalArgumentException("사용자의 정보가 없어서 업데이트할 수 없습니다.");
        }
        if (findById(user.getId()).isEmpty()) {
            throw new IllegalArgumentException("등록되지 않은 사용자 입니다.");
        }
        if (findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }
        users.put(user.getId(), user);
    }

    @Override
    public void deleteAll() {
        users.clear();
    }

}
