package com.kakao.cafe.service.user;

import com.kakao.cafe.dto.user.UserReqDto;
import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.dto.user.UserUpdateReqDto;

import java.util.List;

public interface UserService {
    void addUser(UserReqDto userReqDto);
    List<UserDto> findUsers();
    UserDto findUserById(Long id);
    void updateUser(UserUpdateReqDto userUpdateReqDto);
    UserDto login(UserReqDto userReqDto);
}
