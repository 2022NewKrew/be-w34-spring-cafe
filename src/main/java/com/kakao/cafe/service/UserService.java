package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.SignUp;
import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.domain.repository.UserRepository;
import com.kakao.cafe.service.dto.CredentialsDto;
import com.kakao.cafe.service.dto.SignUpDto;
import com.kakao.cafe.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public UserDto create(SignUpDto signUp) {
        SignUp entity = signUp.toEntity();
        User created = userRepository.create(entity);
        if (created == null) {
            return null;
        }
        return created.toDto();
    }

    public List<UserDto> list() {
        return userRepository.list()
                .stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    @Nullable
    public UserDto get(long id) {
        User found = userRepository.getById(id);
        if (found == null) {
            return null;
        }
        return found.toDto();
    }

    @Nullable
    public UserDto login(CredentialsDto credentials) {
        User found = userRepository.login(
                credentials.getUserId(),
                credentials.getPassword()
        );
        if (found == null) {
            return null;
        }
        return found.toDto();
    }
}
