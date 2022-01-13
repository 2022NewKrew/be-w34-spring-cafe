package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.domain.exception.DuplicateUserIdException;
import com.kakao.cafe.domain.repository.UserRepository;
import com.kakao.cafe.service.dto.SignUpDto;
import com.kakao.cafe.service.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserRepository repository;
    private UserService subject;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        subject = new UserService(repository);
    }

    @Test
    void create() {
        when(repository.getByUserId(anyString())).thenReturn(Optional.empty());
        User user = new User.Builder().build();
        when(repository.create(any())).thenReturn(user);

        SignUpDto signUp = new SignUpDto("userId", "password", "name", "email");
        UserDto result = subject.create(signUp);

        assertNotNull(result);
    }

    @Test
    void create_duplicate() {
        User user = new User.Builder().build();
        when(repository.getByUserId(any())).thenReturn(Optional.of(user));

        SignUpDto signUp = new SignUpDto("userId", "password", "name", "email");
        Executable body = () -> subject.create(signUp);

        assertThrowsExactly(DuplicateUserIdException.class, body);
    }
}
