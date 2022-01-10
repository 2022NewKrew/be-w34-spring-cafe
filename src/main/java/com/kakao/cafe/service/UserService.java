package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserJoinDTO;

public interface UserService {
    User findById(Long id);

    boolean existsById(Long id);

    User join(UserJoinDTO userJoinDTO);
}
