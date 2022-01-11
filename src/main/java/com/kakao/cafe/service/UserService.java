package com.kakao.cafe.service;

import com.kakao.cafe.domain.UserDto;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.NoSuchElementException;

public interface UserService {
    void add(@NonNull final UserDto userDto, @NonNull final String password);
    List<UserDto> getList();
    UserDto getUser(@NonNull final String id) throws NoSuchElementException;
}
