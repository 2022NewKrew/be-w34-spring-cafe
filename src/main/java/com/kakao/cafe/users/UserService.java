package com.kakao.cafe.users;

import com.kakao.cafe.exceptions.DuplicatedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(MemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto signUp(SignUpRequest signUpRequest) {
        if (isDuplicatedUserId(signUpRequest.getUserId())) {
            throw new DuplicatedException("중복 ID 입니다.");
        }

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

    public boolean isDuplicatedUserId(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);

        return user.isPresent();
    }
}
