package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.CreateUserDto;
import com.kakao.cafe.dto.FindUserDto;
import com.kakao.cafe.repository.MemoryUserRepository;
import com.kakao.cafe.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserUseCase {

    private final UserRepository userRepository = new MemoryUserRepository();

    public UUID join(CreateUserDto createUserDto) {
        User user = userRepository.save(createUserDto);
        return user.getUserId();
    }

    public List<User> getAll() {
        return this.userRepository.getAll();
    }

    public Optional<User> findById(FindUserDto findUserDto) {
        return this.userRepository.findById(findUserDto);
    }

}
