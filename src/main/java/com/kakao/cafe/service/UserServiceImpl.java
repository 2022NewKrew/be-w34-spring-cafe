package com.kakao.cafe.service;

import com.kakao.cafe.dto.*;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.exception.user.EditAccountFailedException;
import com.kakao.cafe.exception.user.LoginFailedException;
import com.kakao.cafe.exception.user.UserException;
import com.kakao.cafe.exception.user.UserRegisterFailedException;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDto register(UserDto dto) {
        User entity = dtoToEntity(dto);
        userRepository.save(entity);
        return userRepository.findByEmail(entity)
                .map(this::entityToDto)
                .orElseThrow(UserRegisterFailedException::new);
    }

    @Override
    public AuthDto login(UserDto dto) {
        return userRepository.findByEmail(dtoToEntity(dto))
                .filter(entity -> entity.getPassword().equals(dto.getPassword()))
                .map(this::entityToAuthDto)
                .orElseThrow(LoginFailedException::new);
    }

    @Override
    public UserDto getUser(AuthDto dto) {
        return userRepository.findByEmail(User.builder().email(dto.getEmail()).build())
                .map(this::entityToDto)
                .orElseThrow(UserException::new);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(User.builder().email(email).build())
                .map(this::entityToDto)
                .orElseThrow(UserException::new);
    }

    @Override
    public void modify(EditUserDto dto) {
        userRepository.findByEmail(User.builder().email(dto.getEmail()).build())
                .filter(entity -> entity.getPassword().equals(dto.getPassword()))
                .map(entity -> {
                    entity.changeUsername(dto.getUsername());
                    entity.changePassword(dto.getPassword());
                    userRepository.update(entity);
                    return entity;
                })
                .orElseThrow(EditAccountFailedException::new);
    }

    @Override
    public PageResultDto<UserDto, User> getList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable();
        Page<User> result = userRepository.findAll(pageable);
        Function<User, UserDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<>(result, fn);
    }
}
