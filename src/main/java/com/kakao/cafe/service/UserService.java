package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.SignUp;
import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.domain.exception.DuplicateUserIdException;
import com.kakao.cafe.domain.repository.UserRepository;
import com.kakao.cafe.service.dto.CredentialsDto;
import com.kakao.cafe.service.dto.SignUpDto;
import com.kakao.cafe.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto create(SignUpDto signUp) {
        SignUp entity = signUp.toEntity();
        userRepository.getByUserId(entity.getUserId())
                .ifPresent(user -> { throw new DuplicateUserIdException(); });
        User created = userRepository.create(entity);
        return created.toDto();
    }

    public List<UserDto> list() {
        return userRepository.list()
                .stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    public Optional<UserDto> get(long id) {
        return userRepository.getById(id).map(User::toDto);
    }

    public UserDto login(CredentialsDto credentials) {
        return userRepository.login(
                credentials.getUserId(),
                credentials.getPassword()
        ).toDto();
    }
}
