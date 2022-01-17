package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserCreateDto;
import com.kakao.cafe.dto.UserShowDto;
import com.kakao.cafe.repository.UserJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
