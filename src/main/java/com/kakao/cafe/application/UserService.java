package com.kakao.cafe.application;

import com.kakao.cafe.interfaces.user.dto.request.UserDto;

public interface UserService {
    void join(UserDto userDto);
}
