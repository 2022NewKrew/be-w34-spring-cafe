package com.kakao.cafe.service.user;

import com.kakao.cafe.dto.user.UserRequest;
import com.kakao.cafe.dto.user.UserResponse;
import com.kakao.cafe.dto.user.UserUpdateReqDto;

import java.util.List;

public interface UserService {
    void addUser(UserRequest userRequest);
    List<UserResponse> findUsers();
    UserResponse findUserById(Long id);
    void modifyUser(UserUpdateReqDto userUpdateReqDto);
    UserResponse login(UserRequest userRequest);
}
