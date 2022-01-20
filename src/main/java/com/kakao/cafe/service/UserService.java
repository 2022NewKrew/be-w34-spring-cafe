package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.model.User;
import com.kakao.cafe.repository.UserJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserJdbcRepository userRepository;

    @Autowired
    public UserService(UserJdbcRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserDto userDto){
        userRepository.save(userDto.toEntity());
    }

    public List<UserDto> findAll(){
        return userRepository.findAll()
                .stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    public UserDto findOne(Integer id){
        return userRepository.findOne(id).orElseThrow().toDto();
    }

    public User validate(UserDto userDto){
        Optional<User> user = userRepository.findByUserId(userDto.getUserId());
        if(user.isEmpty()){
            throw new IllegalArgumentException("해당 아이디의 유저를 찾을 수 없습니다");
        }
        if(!user.orElseThrow().matchPassword(userDto.getPassword())){
            throw new IllegalArgumentException("유저 아이디와 패스워드가 일치하지 않습니다");
        }
        return user.orElseThrow();
    }


    public void update(Integer id, UserDto userDto){
        userRepository.update(id, userDto);
    }
}
