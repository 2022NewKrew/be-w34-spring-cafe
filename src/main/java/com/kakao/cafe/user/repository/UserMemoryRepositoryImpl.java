package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserMemoryRepositoryImpl implements UserRepository {
    private final static AtomicLong idSequence = new AtomicLong();
    private final static HashMap<Long, User> userDB = new HashMap<>();

    public User find(Long id) {
        return userDB.get(id);
    }

    public ArrayList<User> findAll() {
        return new ArrayList<>(userDB.values());
    }

    public Long persist(UserCreateRequestDTO dto) {
        User user = User.builder()
                .id(idSequence.get())
                .stringId(dto.stringId)
                .email(dto.email)
                .nickName(dto.nickName)
                .password(dto.password)
                .signUpDate(dto.signUpDate)
                .build();
        userDB.put(idSequence.get(), user);
        return idSequence.getAndIncrement();
    }

    public Long findDBIdById(String stringId) {
        return userDB.keySet().stream().filter(key -> stringId.equals(userDB.get(key).getStringId())).findAny().orElseGet(() -> -1L);
    }

    public String findStringIdByDBId(Long id) {
        return userDB.get(id).getStringId();
    }

    public String findPasswordByDBId(Long userId) {
        return userDB.get(userId).getPassword();
    }

    public void updateUserInfo(UserUpdateRequestDTO dto) {
        User oldUserData = userDB.get(dto.getUserId());
        User user = User.builder()
                .id(oldUserData.getId())
                .stringId(oldUserData.getStringId())
                .email(dto.getEmail())
                .nickName(dto.getName())
                .password(dto.getNewPassword())
                .signUpDate(oldUserData.getSignUpDate())
                .build();
        userDB.put(dto.getUserId(), user);
    }
}
