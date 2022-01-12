package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepositoryNoDB;
import com.kakao.cafe.dto.UserRequestDto;
import com.kakao.cafe.dto.UserResponseDto;
import com.kakao.cafe.dto.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    //DB생성 전까지 users 변수에 임시 저장
    private UserRepositoryNoDB userRepositoryNoDB = new UserRepositoryNoDB();

    public void save(UserRequestDto userRequestDto){
        userRepositoryNoDB.save(UserMapper.INSTANCE.toEntity(userRequestDto));
    }

    public void update(int id, UserRequestDto userRequestDto){
        User user = userRepositoryNoDB.findById(id);
        if (userRequestDto.getStringId().equals(user.getStringId()) && userRequestDto.getPrevPassword().equals(user.getPassword())){
            userRepositoryNoDB.update(id, UserMapper.INSTANCE.toEntity(userRequestDto));
        }
    }

    public List<UserResponseDto> findAll(){
        return UserMapper.INSTANCE.toDtoList(userRepositoryNoDB.findAll());
    }

    public UserResponseDto findbyId(int id){
        return UserMapper.INSTANCE.toDto(userRepositoryNoDB.findById(id));
    }
}
