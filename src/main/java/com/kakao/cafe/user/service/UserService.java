package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserCreateDTO;
import com.kakao.cafe.user.dto.UserListDTO;
import com.kakao.cafe.user.dto.UserProfileDTO;
import com.kakao.cafe.user.repository.UserJdbcRepository;
import com.kakao.cafe.user.repository.UserMemoryRepository;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    //private UserRepository userRepository = new UserMemoryRepository();
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public void userCreate(UserCreateDTO userCreateDTO){
        userRepository.addUser(userCreateDTO);
    }

    public List<User> getAllUser(){
        return userRepository.getUsers();
    }

    //하나의 유저를 찾으려고 하더라도 모든 유저의 정보를 긁어와야하는 연산이 매우 오버헤드가 클 것임, repository에서 따로 select하는 구문으로 수정해야할 것.
    public User getUserByUserId(String userId){
        Optional<User> optionalUser = userRepository.getUsers().stream().filter((user) -> {return user.getUserId().equals(userId);}).findAny();
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }

        throw new RuntimeException("유저 아이디에 해당하는 유저가 없습니다.");
    }
}
