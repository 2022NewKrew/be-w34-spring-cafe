package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class UserRepository {
    private static Long idSequence = 0L;
    private final static HashMap<Long, User> userDB = new HashMap<>();

    private final static UserRepository userRepository = new UserRepository();

    public static UserRepository getRepository() {
        return userRepository;
    }

    public User find(Long id) {
        return userDB.get(id);
    }

    public ArrayList<User> findAll() {
        return new ArrayList<>(userDB.values());
    }

    public Long persist(CreateUserDTO dto) {
        userDB.put(idSequence, new User(idSequence, dto.email, dto.nickName, dto.password, dto.signUpDate));
        idSequence += 1;
        return idSequence - 1;
    }

}
