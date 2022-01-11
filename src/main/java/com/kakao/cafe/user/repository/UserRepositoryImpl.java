package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.SignUpReq;
import com.kakao.cafe.user.exception.UserIdDuplicateException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Map<String, User> userTable = new HashMap<>();

    private UserRepositoryImpl() {
        User user1 = User.of("ltk3934", "lee7431!", "이태경", "ltk3934@daum.net");
        userTable.put(user1.getUserId(), user1);
    }

    @Override
    public void save(SignUpReq req) {
        if(userTable.containsKey(req.getUserId()))
            throw new UserIdDuplicateException();
        User user = req.toEntity();
        userTable.put(user.getUserId(), user);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return Optional.ofNullable(userTable.getOrDefault(userId, null));
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(userTable.values());
    }
}
