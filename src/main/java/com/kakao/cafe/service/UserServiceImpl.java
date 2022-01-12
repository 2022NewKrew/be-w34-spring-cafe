package com.kakao.cafe.service;

import com.kakao.cafe.dto.AuthDto;
import com.kakao.cafe.dto.PageRequestDto;
import com.kakao.cafe.dto.PageResultDto;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        try {
            return entityToDto(userRepository.findbyIdAndPassword(entity));
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @Override
    public AuthDto login(UserDto dto) {
        User entity = dtoToEntity(dto);
        try {
            return entityToDto(userRepository.findbyIdAndPassword(entity)).getAuthDto();
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @Override
    public PageResultDto<UserDto, User> getList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable();
        Page<User> result = userRepository.findAll(pageable);
        Function<User, UserDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<>(result, fn);
    }
}
