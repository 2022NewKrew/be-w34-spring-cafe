package com.kakao.cafe.service;

import com.kakao.cafe.User;
import com.kakao.cafe.dto.CreateUserDto;
import com.kakao.cafe.repository.MemoryUserRepository;
import com.kakao.cafe.repository.UserRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository = new MemoryUserRepository();

    public UUID join(CreateUserDto createUserDto) {
        User user = userRepository.save(createUserDto);
        return user.getUser_id();
    }

}
