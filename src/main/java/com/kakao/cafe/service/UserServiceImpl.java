package com.kakao.cafe.service;

import com.kakao.cafe.dto.*;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.exception.CafeException;
import com.kakao.cafe.exception.UserException;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto register(UserDto dto) {
        User entity = dtoToEntity(dto);
        userRepository.save(entity);
        Optional<User> result = userRepository.findbyEmailAndPassword(entity);
        if (result.isEmpty())
            throw new UserException();
        return entityToDto(result.get());
    }

    @Override
    public AuthDto login(UserDto dto) {
        User entity = dtoToEntity(dto);
        Optional<User> result = userRepository.findbyEmailAndPassword(entity);
        if (result.isEmpty())
            throw new UserException();
        return entityToDto(result.get()).getAuthDto();
    }

    @Override
    public UserDto getUser(AuthDto dto) {
        Optional<User> result = userRepository.findbyEmail(User.builder().email(dto.getEmail()).build());
        if (result.isEmpty())
            throw new UserException();
        return entityToDto(result.get());
    }

    @Override
    public UserDto update(EditUserDto dto) {
        if (!dto.confirmPassword())
            throw new UserException();
        User.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.)
        return null;
    }

    @Override
    public PageResultDto<UserDto, User> getList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable();
        Page<User> result = userRepository.findAll(pageable);
        Function<User, UserDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<>(result, fn);
    }
}
