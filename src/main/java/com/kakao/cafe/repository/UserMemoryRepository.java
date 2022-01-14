package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.UserSignUpDTO;
import com.kakao.cafe.domain.dto.UserViewDTO;
import com.kakao.cafe.domain.model.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

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
