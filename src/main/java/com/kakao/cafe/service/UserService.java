package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepositoryList;
import com.kakao.cafe.dto.UserRequestDto;
import com.kakao.cafe.dto.UserResponseDto;
import com.kakao.cafe.dto.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    //DB생성 전까지 users 변수에 임시 저장
    private UserRepositoryList userRepositoryList = new UserRepositoryList();

    public void save(UserRequestDto userRequestDto){
        userRepositoryList.save(UserMapper.INSTANCE.toEntity(userRequestDto));
    }

    public void update(int id, UserRequestDto userRequestDto){
        User user = userRepositoryList.findById(id);
        if (userRequestDto.getStringId().equals(user.getStringId()) && userRequestDto.getPrevPassword().equals(user.getPassword())){
            userRepositoryList.update(id, UserMapper.INSTANCE.toEntity(userRequestDto));
        }
    }

    public List<UserResponseDto> findAll(){
        return UserMapper.INSTANCE.toDtoList(userRepositoryList.findAll());
    }

    public UserResponseDto findbyId(int id){
        return UserMapper.INSTANCE.toDto(userRepositoryList.findById(id));
    }
}
