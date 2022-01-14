package com.kakao.cafe.repository;

import com.kakao.cafe.model.User;
import com.kakao.cafe.model.UserSignUpDTO;
import com.kakao.cafe.model.UserViewDTO;
import com.kakao.cafe.model.Users;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserMemoryRepository implements UserRepository {

    private final Users users = new Users();

    @Override
    public void signUp(UserSignUpDTO userSignUpDTO) {
        users.addUser(userSignUpDTO);
    }

    @Override
    public List<UserViewDTO> findAllUsers() {
        return users.getAllUsers();
    }

    @Override
    public UserViewDTO findUserByUserId(String userId) {
        return users.findUserById(userId);
    }
}
