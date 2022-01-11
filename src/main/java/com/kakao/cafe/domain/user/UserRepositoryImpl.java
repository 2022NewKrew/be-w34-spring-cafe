package com.kakao.cafe.domain.user;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();

    private Long SEQ_NO_OF_USERS = 0L;

    @Override
    public void save(User user) throws IllegalArgumentException {
        if (ObjectUtils.isEmpty(user)) {
            throw new IllegalArgumentException("사용자의 정보가 없어서 저장할 수 없습니다.");
        }
        user.setId(++SEQ_NO_OF_USERS);
        users.put(SEQ_NO_OF_USERS, user);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void deleteAll() {
        users.clear();
    }

}
