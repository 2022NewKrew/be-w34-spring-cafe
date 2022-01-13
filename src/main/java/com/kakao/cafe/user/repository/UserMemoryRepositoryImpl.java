package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserMemoryRepositoryImpl implements UserRepository{
    private static AtomicLong idSequence = new AtomicLong();
    private final static HashMap<Long, User> userDB = new HashMap<>();

    public UserMemoryRepositoryImpl() {
        // 기본 유저 생성
        persist(new UserCreateRequestDTO("aiden.jang", "aiden@kakaocorp.com", "aiden", "1234", LocalDateTime.now()));
    }

    public User find(Long id) {
        return userDB.get(id);
    }

    public ArrayList<User> findAll() {
        return new ArrayList<>(userDB.values());
    }

    public Long persist(UserCreateRequestDTO dto) {
        userDB.put(idSequence.get(), new User(idSequence.get(), dto.stringId, dto.email, dto.nickName, dto.password, dto.signUpDate));

//Article article = jdbcTemplate.queryForObject("SELECT * FROM ARTICLE WHERE ID = ?", rowMapper, id);
        

        return idSequence.getAndIncrement();
    }

    public Long findDBIdById(String stringId) {
        return userDB.keySet().stream().filter(key->stringId.equals(userDB.get(key).getStringId())).findAny().orElseGet(()->-1L);
    }

    public String findStringIdByDBId(Long id) {
        return userDB.get(id).getStringId();
    }

    public String findPasswordByDBId(Long userId) {
        return userDB.get(userId).getPassword();
    }

    public void updateUserInfo(UserUpdateRequestDTO dto) {
        User oldUserData = userDB.get(dto.getUserId());
        User user = new User(oldUserData.getId(), oldUserData.getStringId(), dto.getEmail(), dto.getName(), dto.getNewPassword(), oldUserData.getSignUpDate());
        userDB.put(dto.getUserId(), user);
    }
}
