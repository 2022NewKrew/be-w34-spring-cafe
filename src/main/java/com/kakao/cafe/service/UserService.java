package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private static long idIndex = 1;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(String userId,String password,String email){
        userRepository.create(new User(idIndex,userId,password,email));
        idIndex++;
    }

    public User getUserByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

    public List<User> getUserList(){
        return userRepository.getUserList();
    }

    public int getUserListSize() {
        return userRepository.getUserList().size();
    }
}
