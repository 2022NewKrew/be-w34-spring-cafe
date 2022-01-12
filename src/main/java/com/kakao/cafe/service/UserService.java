package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.Users;
import com.kakao.cafe.dto.UserRequestDto;
import com.kakao.cafe.dto.UserResponseDto;
import com.kakao.cafe.dto.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    //DB생성 전까지 users 변수에 임시 저장
    private Users users = new Users();

    public void save(UserRequestDto userRequestDto){
        users.addUser(UserMapper.INSTANCE.toEntity(userRequestDto));
    }

    public void update(int id, UserRequestDto userRequestDto){
        users.updateUser(id, UserMapper.INSTANCE.toEntity(userRequestDto));
    }

    public List<UserResponseDto> findAll(){
        return UserMapper.INSTANCE.toDtoList(users.getUsers());
    }

    public UserResponseDto findbyId(int id){
        return UserMapper.INSTANCE.toDto(users.findById(id));
    }
}
