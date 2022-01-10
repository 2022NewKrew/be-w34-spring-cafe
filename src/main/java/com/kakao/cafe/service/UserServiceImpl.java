package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String register(UserDto dto) {
        User entity = dtoToEntity(dto);
        log.info(entity);
        userRepository.save(entity);
        return entity.getEmail();
    }
}
