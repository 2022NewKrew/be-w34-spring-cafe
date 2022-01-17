package com.kakao.cafe.users;

import com.kakao.cafe.exceptions.BadCredentialException;
import com.kakao.cafe.exceptions.DuplicatedException;
import com.kakao.cafe.exceptions.NotFoundException;
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

    public UserDto login(String userId, String password) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException("아이디가 존재하지 않습니다."));

        if (!user.getPassword().equals(password)) {
            throw new BadCredentialException("비밀번호가 일치하지 않습니다.");
        }

        return UserDto.toDto(user);
    }

    public boolean isDuplicatedUserId(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);

        return user.isPresent();
    }
}
