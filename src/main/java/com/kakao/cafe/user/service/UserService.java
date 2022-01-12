package com.kakao.cafe.user.service;

import com.kakao.cafe.user.model.User;
import com.kakao.cafe.user.model.UserDto;
import com.kakao.cafe.user.model.UserProfileDto;
import com.kakao.cafe.user.model.UserRequest;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signUp(UserRequest userRequest){
        Long id = userRepository.getNumberOfUsers() + 1;
        userRepository.save(userRequest.toEntity(id));
    }

    public List<UserDto> getUsersList(){
        return userRepository.findAll()
                .stream().map(UserDto::of)
                .collect(Collectors.toList());
    }

    public UserProfileDto getUserProfile(String userId){
        User user = userRepository.findOneByUserId(userId);
        return UserProfileDto.of(user);
    }
}
