package com.kakao.cafe.service.user;

import com.kakao.cafe.dto.user.UserReqDto;
import com.kakao.cafe.dto.user.UserResDto;
import com.kakao.cafe.dto.user.UserUpdateReqDto;

import java.util.List;

public interface UserService {
    void addUser(UserReqDto userReqDto);
    List<UserResDto> findUsers();
    UserResDto findUserById(Long id);
    void updateUser(UserUpdateReqDto userUpdateReqDto);
    UserResDto login(UserReqDto userReqDto);
}
