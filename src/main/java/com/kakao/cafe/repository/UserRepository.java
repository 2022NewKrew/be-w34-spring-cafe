package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.util.*;

/**
 * DB 연동 전 임시 사용
 */
public class UserRepository implements Repository<User, Long> {
    private Map<Long, User> users = new TreeMap<>();

    @Override
    public Optional<User> findById(Long id) {
        if (users.containsKey(id)) {
            return Optional.of(users.get(id));
        }
        return Optional.empty();
    }

    public Optional<User> findByEmail(String email) {
        for (Map.Entry<Long, User> userEntry : users.entrySet()) {
            User user = userEntry.getValue();
            if (user.getEmail().equals(email)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public List<User> findAll(Integer pageNum, Integer pageSize) {
        Integer startIndex = (pageNum -1) * pageSize;
        return new ArrayList<>();
    }

    public boolean existsByEmail(String email) {
        for (Map.Entry<Long, User> userEntry : users.entrySet()) {
            User user = userEntry.getValue();
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean existsByNickName(String nickName) {
        for (Map.Entry<Long, User> userEntry : users.entrySet()) {
            User user = userEntry.getValue();
            if (user.getNickName().equals(nickName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsById(Long id) {
        return users.containsKey(id);
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(getNextId());
        }
        return users.put(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        Long userId = user.getId();
        users.remove(userId);
    }

    public Long getMaxId() {
        return users.keySet().stream()
                .max(Long::compareTo)
                .orElseGet(() -> Long.valueOf(0));
    }

    private Long getNextId() {
        Long currentMaxID = getMaxId();
        return currentMaxID + 1;
    }

}
