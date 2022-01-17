package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.dto.mapper.UserMapper;
import com.kakao.cafe.dto.user.UserResponseDto;
import com.kakao.cafe.dto.user.UserSaveDto;
import com.kakao.cafe.dto.user.UserUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    @Qualifier("UserRepositoryJdbc")
    private UserRepository userRepository;

    public void save(UserSaveDto userSaveDto){
        if (userRepository.findByStringId(userSaveDto.getStringId()) == null){
            userRepository.save(UserMapper.INSTANCE.toEntityFromSaveDto(userSaveDto));
        }
    }

    public void update(int id, UserUpdateDto userUpdateDto){
        User user = userRepository.findById(id);
        if (userUpdateDto.getPrevPassword().equals(user.getPassword())){
            User userFromUpdateDto = UserMapper.INSTANCE.toEntityFromUpdateDto(userUpdateDto);
            user.changePassword(userFromUpdateDto.getPassword());
            user.changeName(userFromUpdateDto.getName());
            user.changeEmail(userFromUpdateDto.getEmail());
            userRepository.save(user);
        }
    }

    public List<UserResponseDto> findAll(){
        return UserMapper.INSTANCE.toDtoList(userRepository.findAll());
    }

    public UserResponseDto findbyId(int id){
        return UserMapper.INSTANCE.toDto(userRepository.findById(id));
    }

}
