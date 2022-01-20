package com.kakao.cafe.user.service;

import java.util.List;

import com.kakao.cafe.common.SessionUser;
import com.kakao.cafe.user.dto.request.UserCreateRequestDTO;
import com.kakao.cafe.user.dto.request.UserLoginRequestDTO;
import com.kakao.cafe.user.dto.request.UserUpdateRequestDTO;
import com.kakao.cafe.user.dto.response.UserFindResponseDTO;
import com.kakao.cafe.user.dto.response.UserInfoResponseDTO;

public interface UserService {
    void create(UserCreateRequestDTO userCreateRequestDTO);

    List<UserFindResponseDTO> getAllUser();

    UserFindResponseDTO getUserById(int id);

    UserInfoResponseDTO getUserInfoById(int id);

    void update(int id, UserUpdateRequestDTO userUpdateRequestDTO);

    SessionUser checkLogin(UserLoginRequestDTO userLoginRequestDTO);
}
