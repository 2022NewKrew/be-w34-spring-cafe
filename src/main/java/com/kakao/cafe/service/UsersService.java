package com.kakao.cafe.service;

import com.kakao.cafe.domain.users.UsersRepository;
import com.kakao.cafe.web.dto.UserResponseDto;
import com.kakao.cafe.web.dto.UsersCreateRequestDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public class UsersService {
    private final UsersRepository usersRepository = new UsersRepository();

    @Transactional
    public void save(UsersCreateRequestDto requestDto) {
        usersRepository.save(requestDto.toEntity());
    }

    @Transactional(readOnly = true)
    public ArrayList<UserResponseDto> findAllUsers() {
        return usersRepository.findAll();
    }

    @Transactional
    public UserResponseDto findById(Long id) {
        return usersRepository.findById(id);
    }

    @Transactional
    public void update(Long id, UsersCreateRequestDto requestDto) {
        usersRepository.update(id, requestDto.toEntity());
    }
}
