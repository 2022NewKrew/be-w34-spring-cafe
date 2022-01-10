package com.kakao.cafe.user.service;

import com.kakao.cafe.user.model.User;
import com.kakao.cafe.user.model.UserDto;
import com.kakao.cafe.user.model.UserProfileDto;
import com.kakao.cafe.user.model.UserRequest;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(UserRequest userRequest){
        Long numberOfUsers = userRepository.getNumberOfUsers();
        userRepository.save(new User(numberOfUsers + 1, userRequest));
    }

    public List<UserDto> getUsersList(){
        return userRepository.find()
                .stream().map(User::toUserDto)
                .collect(Collectors.toList());
    }

    public UserProfileDto getUserProfile(String userId){
        return userRepository.findOneByUserId(userId).toProfileDto();
    }
}
