package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.domain.exception.DuplicateUserIdException;
import com.kakao.cafe.domain.exception.UnauthorizedException;
import com.kakao.cafe.domain.repository.UserRepository;
import com.kakao.cafe.service.dto.ModifyUserDto;
import com.kakao.cafe.service.dto.SignUpDto;
import com.kakao.cafe.service.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
        when(repository.getByUserId(anyString())).thenReturn(Optional.of(user));

        SignUpDto signUp = new SignUpDto("userId", "password", "name", "email");
        Executable body = () -> subject.create(signUp);

        assertThrowsExactly(DuplicateUserIdException.class, body);
    }

    @Test
    void modify() {
        User user = new User.Builder()
                .userId("userId")
                .password("password")
                .name("name")
                .email("email")
                .build();
        when(repository.getById(anyLong())).thenReturn(Optional.of(user));
        when(repository.getByUserId(anyString())).thenReturn(Optional.empty());
        when(repository.update(anyLong(), any())).thenReturn(user);
        ModifyUserDto modifyUser = new ModifyUserDto("password", "name", "email");

        UserDto result = subject.modify(1L, 1L, modifyUser);

        verify(repository).update(eq(1L), any());
        assertEquals(user.toDto(), result);
    }

    @Test
    void modify_wrongPassword() {
        User user = new User.Builder()
                .userId("userId")
                .password("password")
                .name("name")
                .email("email")
                .build();
        when(repository.getById(anyLong())).thenReturn(Optional.of(user));
        when(repository.getByUserId(anyString())).thenReturn(Optional.empty());
        ModifyUserDto modifyUser = new ModifyUserDto("wrong", "name", "email");

        Executable body = () -> subject.modify(1L, 1L, modifyUser);

        assertThrowsExactly(UnauthorizedException.class, body);
    }

    @Test
    void modify_notTheUserHerself() {
        User.Builder userBuilder = new User.Builder()
                .userId("userId")
                .password("password")
                .name("name")
                .email("email");
        User user1 = userBuilder.id(1L).build();
        User user2 = userBuilder.id(2L).build();
        when(repository.getById(1L)).thenReturn(Optional.of(user1));
        when(repository.getById(2L)).thenReturn(Optional.of(user2));
        ModifyUserDto modifyUser = new ModifyUserDto("password", "name", "email");

        Executable body = () -> subject.modify(1L, 2L, modifyUser);

        assertThrowsExactly(UnauthorizedException.class, body);
    }
}
