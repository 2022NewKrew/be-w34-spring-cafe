package com.kakao.cafe.user.service;

import com.kakao.cafe.user.exception.UserAlreadyExistException;
import com.kakao.cafe.user.exception.UserNotExistException;
import com.kakao.cafe.user.model.User;
import com.kakao.cafe.user.dto.UserDto;
import com.kakao.cafe.user.dto.UserProfileDto;
import com.kakao.cafe.user.dto.UserRequest;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public void signUp(UserRequest userRequest){
        if(isExistUser(userRequest.getUserId())){
            throw new UserAlreadyExistException();
        }

        User user = modelMapper.map(userRequest, User.class);
        user.setId(userRepository.getNumberOfUsers() + 1);
        userRepository.save(user);
    }

    public List<UserDto> getUsersList(){
        return userRepository.findAll()
                .stream().map(user -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
    }

    public UserProfileDto getUserProfile(String userId){
        User user = userRepository.findOneByUserId(userId).orElseThrow(UserNotExistException::new);
        return modelMapper.map(user, UserProfileDto.class);
    }

    public boolean isExistUser(String userId){
        return userRepository.findOneByUserId(userId).isPresent();
    }
}
