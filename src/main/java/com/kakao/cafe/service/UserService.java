package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public void userCreate(User user){
        userRepository.userAdd(user);
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
        return new User("", "", "", ""); //의미없는 user객체를 반환 .
    }

    public void setNextUserSequence(User user){
        if(user == null){
            return;
        }
        user.setSequence(Long.valueOf(userRepository.getUsers().size() + 1));
    }
}
