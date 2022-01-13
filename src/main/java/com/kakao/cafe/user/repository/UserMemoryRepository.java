package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.SignUpDTO;
import com.kakao.cafe.user.dto.UpdateDTO;
import com.kakao.cafe.user.factory.UserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class UserMemoryRepository implements UserRepository {

    private final UserFactory userFactory;
    private final static Map<Long, User> userMap = new TreeMap<>();

    @Override
    public void save(SignUpDTO signUpDTO) {
        User user = userFactory.of(userMap.size() + 1L, signUpDTO);
        userMap.put(userMap.size() + 1L, user);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userMap.values().stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(userMap.values());
    }

    @Override
    public void update(UpdateDTO updateDTO) {
        User user = userMap.get(updateDTO.getId());
        user.updateInfo(updateDTO);
    }
}
