package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserCreateDto;
import com.kakao.cafe.dto.UserLoginDto;
import com.kakao.cafe.dto.UserShowDto;
import com.kakao.cafe.dto.UserUpdateDto;
import com.kakao.cafe.model.User;
import com.kakao.cafe.repository.UserJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserJdbcRepository userRepository;

    @Autowired
    public UserService(UserJdbcRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserCreateDto userCreateDto){
        userRepository.save(userCreateDto.toEntity());
    }

    public List<UserShowDto> findAll(){
        return userRepository.findAll()
                .stream()
                .map(UserShowDto::new)
                .collect(Collectors.toList());
    }

    public UserShowDto findOne(Integer id){
        return new UserShowDto(userRepository.findOne(id).orElseThrow());
    }

    public User validate(UserLoginDto userLoginDto){
        Optional<User> user = userRepository.findByUserId(userLoginDto.getUserId());
        if(user.isEmpty()){
            throw new IllegalArgumentException("해당 아이디의 유저를 찾을 수 없습니다");
        }
        if(!user.orElseThrow().matchPassword(userLoginDto.getPassword())){
            throw new IllegalArgumentException("유저 아이디와 패스워드가 일치하지 않습니다");
        }
        return user.orElseThrow();
    }


    public void update(Integer id, UserUpdateDto userUpdateDto){
        userRepository.update(id, userUpdateDto);
    }
}
