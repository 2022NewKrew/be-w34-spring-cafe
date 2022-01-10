package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UserRepository {
    private static Long idSequence = 0L;
    private static HashMap<Long, User> userDB = new HashMap<>();

    private static UserRepository userRepository = new UserRepository();

    private UserRepository() {
    }

    public static UserRepository getRepository() {
        return userRepository;
    }

    public User find(Long id) {
        return userDB.get(id);
    }

    public ArrayList<User> findAll() {
        return new ArrayList<>(userDB.values());
    }

    public Long persist(CreateUserDTO dto) throws IllegalArgumentException{
        userDB.put(idSequence, new User(idSequence, dto.email, dto.nickName, dto.password, dto.signUpDate));
        idSequence += 1;
        return idSequence;
    }

    public void clear() {
        idSequence = 0L;
        userDB.clear();
    }

    public Long getLastIdSequenceNumber() {
        return idSequence;
    }
}
