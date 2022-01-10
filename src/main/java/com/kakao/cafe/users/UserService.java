package com.kakao.cafe.users;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(MemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto signUp(SignUpRequest signUpRequest) {
        User user = new User(signUpRequest.getUserId(), signUpRequest.getPassword(), signUpRequest.getName(), signUpRequest.getEmail());

        User savedUser = userRepository.save(user);

        return UserDto.toDto(savedUser);
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::toDto)
                .collect(Collectors.toList());
    }
}
