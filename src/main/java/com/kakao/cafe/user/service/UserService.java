package com.kakao.cafe.user.service;

import com.kakao.cafe.user.exception.UserAlreadyExistException;
import com.kakao.cafe.user.exception.UserNotExistException;
import com.kakao.cafe.user.model.User;
import com.kakao.cafe.user.dto.UserDto;
import com.kakao.cafe.user.dto.UserProfileDto;
import com.kakao.cafe.user.dto.UserRequest;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signUp(UserRequest userRequest){
        if(isExistUser(userRequest.getUserId())){
            throw new UserAlreadyExistException();
        }

        Long id = userRepository.getNumberOfUsers() + 1;
        userRepository.save(userRequest.toEntity(id));
    }

    public List<UserDto> getUsersList(){
        return userRepository.findAll()
                .stream().map(UserDto::of)
                .collect(Collectors.toList());
    }

    public UserProfileDto getUserProfile(String userId){
        if(!isExistUser(userId)) throw new UserNotExistException();
        User user = userRepository.findOneByUserId(userId);
        return UserProfileDto.of(user);
    }

    public boolean isExistUser(String userId){
        return Optional.ofNullable(userRepository.findOneByUserId(userId)).isPresent();
    }
}
