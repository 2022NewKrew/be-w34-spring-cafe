package com.kakao.cafe.service;

import com.kakao.cafe.entity.User;
import com.kakao.cafe.dto.UserDto;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.NoSuchElementException;

public interface UserService {
    void add(@NonNull final UserDto userDto, @NonNull final String password);
    List<UserDto> getList();
    UserDto getDto(@NonNull final String id) throws NoSuchElementException;
    User get(@NonNull final String id) throws NoSuchElementException;
    boolean verifyPassword(@NonNull final String id, @NonNull final String rawPassword);
    boolean update(@NonNull final UserDto userDto, @NonNull final String rawPassword, @NonNull final String newRawPassword);
}
