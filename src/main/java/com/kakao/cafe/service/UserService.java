package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.user.ProfileUpdateDto;
import com.kakao.cafe.dto.user.SimpleUserInfo;
import com.kakao.cafe.dto.user.UserJoinDto;

import java.util.List;

public interface UserService {
    User findById(Long id);

    User findByEmail(String email);

    List<SimpleUserInfo> getListOfSimpleUserInfo(Integer pageNum, Integer pageSize);

    boolean existsById(Long id);

    User join(UserJoinDto userJoinDTO);

    User updateProfile(ProfileUpdateDto profileUpdateDto);

    int countAll();
}
