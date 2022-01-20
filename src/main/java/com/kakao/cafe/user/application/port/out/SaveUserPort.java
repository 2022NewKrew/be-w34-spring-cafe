package com.kakao.cafe.user.application.port.out;

import com.kakao.cafe.user.domain.UserId;

public interface SaveUserPort {

    UserId save(CreateUserDto createUserDto);

}
