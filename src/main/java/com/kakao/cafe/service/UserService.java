package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.dto.user.UserRequestDto;
import com.kakao.cafe.dto.user.UserResponseDto;
import com.kakao.cafe.dto.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    @Qualifier("UserRepositoryJdbc")
    private UserRepository userRepository;

    public void save(UserRequestDto userRequestDto){
        if (userRepository.findByStringId(userRequestDto.getStringId()) == null){
            userRepository.save(UserMapper.INSTANCE.toEntity(userRequestDto));
        }
    }

    public void update(int id, UserRequestDto userRequestDto){
        User user = userRepository.findById(id);
        if (userRequestDto.getStringId().equals(user.getStringId()) && userRequestDto.getPrevPassword().equals(user.getPassword())){
            userRepository.save(user.update(UserMapper.INSTANCE.toEntity(userRequestDto)));
        }
    }

    public List<UserResponseDto> findAll(){
        return UserMapper.INSTANCE.toDtoList(userRepository.findAll());
    }

    public UserResponseDto findbyId(int id){
        return UserMapper.INSTANCE.toDto(userRepository.findById(id));
    }

}
