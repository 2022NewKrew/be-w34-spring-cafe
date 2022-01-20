package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserDto;
import com.kakao.cafe.domain.UserMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImplMemoryDB implements UserRepository {

    private final Map<String, User> userMemoryDB = new ConcurrentHashMap<>();
    private static final UserMapper MAPPER = UserMapper.INSTANCE;

    @Override
    public void createUser(UserDto userDto) {
        String uid = UUID.randomUUID().toString();
        User user = MAPPER.toUserEntity(userDto, uid);
        userMemoryDB.put(userDto.getUserId(), user);
    }

    @Override
    public boolean isUserIdUsed(String userId) {
        return userMemoryDB.containsKey(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(userMemoryDB.values());
    }

    @Override
    public User findUserByUserId(String userId) {
        return userMemoryDB.get(userId);
    }

    @Override
    public User updateUser(User user) {
        return user;
    }

    @Override
    public String findUidByUserId(String userId) {
        User user = userMemoryDB.get(userId);
        return user.getUid();
    }
}
