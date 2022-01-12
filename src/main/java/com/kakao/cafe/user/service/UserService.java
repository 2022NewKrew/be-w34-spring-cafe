package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserCreateDTO;
import com.kakao.cafe.user.dto.UserListDTO;
import com.kakao.cafe.user.dto.UserProfileDTO;
import com.kakao.cafe.user.repository.UserMemoryRepository;
import com.kakao.cafe.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private UserRepository userRepository = new UserMemoryRepository();

    public void userCreate(UserCreateDTO userCreateDTO){
        userRepository.addUser(userCreateDTO);
    }

    public List<User> getAllUser(){
        return userRepository.getUsers();
    }

    public User getUserByUserId(String userId){
        for(User user : userRepository.getUsers()){
            if(user.getUserId().equals(userId)){
                return user;
            }
        }

        throw new RuntimeException("유저 아이디에 해당하는 유저가 없습니다.");
    }
}
