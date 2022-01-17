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

    public User save(UserSaveDto userSaveDto){
        if (userRepository.findByStringId(userSaveDto.getStringId()) == null){
            return userRepository.save(UserMapper.INSTANCE.toEntityFromSaveDto(userSaveDto));
        }
        throw new IllegalArgumentException("입력된 ID가 이미 존재합니다.");
    }

    public User update(int id, UserUpdateDto userUpdateDto){
        User user = userRepository.findById(id);
        if (userUpdateDto.getPrevPassword().equals(user.getPassword())){
            User userFromUpdateDto = UserMapper.INSTANCE.toEntityFromUpdateDto(userUpdateDto);
            user.changePassword(userFromUpdateDto.getPassword());
            user.changeName(userFromUpdateDto.getName());
            user.changeEmail(userFromUpdateDto.getEmail());
            return userRepository.save(user);
        }
        throw new IllegalArgumentException("기존 비밀번호와 입력된 비밀번호가 불일치 합니다.");
    }

    public List<UserResponseDto> findAll(){
        return UserMapper.INSTANCE.toDtoList(userRepository.findAll());
    }

    public UserResponseDto findbyId(int id){
        return UserMapper.INSTANCE.toDto(userRepository.findById(id));
    }

    public User login(String stringId, String password){
        User user = userRepository.findByStringId(stringId);
        if(user!=null && user.getPassword().equals(password)){
            return user;
        }
        throw new IllegalArgumentException("로그인 비밀번호와 DB비밀번호가 불일치 합니다.");
    }
}
