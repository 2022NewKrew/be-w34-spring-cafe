package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UserRepository {
    private static Long idSequence = 0L;
    private final static HashMap<Long, User> userDB = new HashMap<>();

    private final static UserRepository userRepository = new UserRepository();

    public static UserRepository getRepository() {
        return userRepository;
    }

    private UserRepository() {
    }

    public void clear() {
        idSequence = 0L;
        userDB.clear();
    }

    public User find(Long id) {
        return userDB.get(id);
    }

    public ArrayList<User> findAll() {
        return new ArrayList<>(userDB.values());
    }

    public void persist(CreateUserDTO dto) {
        userDB.put(idSequence, new User(idSequence, dto.email, dto.nickName, dto.password, dto.signUpDate));
        idSequence += 1;
    }

}
