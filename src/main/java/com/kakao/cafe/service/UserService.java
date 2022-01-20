package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.SampleUserForm;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> printUsers(){
        return userRepository.getUserList();
    }

    public User findUser(String userID){
        return userRepository.findUser(userID);
    }

    public Boolean addUser(SampleUserForm form){
        return userRepository.addUser(form);
    }

    public Boolean updateUser(SampleUserForm form){
        return userRepository.updateUser(form);
    }
}
