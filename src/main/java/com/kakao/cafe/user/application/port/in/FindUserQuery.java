package com.kakao.cafe.user.application.port.in;

import com.kakao.cafe.user.domain.UserId;
import java.util.List;

public interface FindUserQuery {

    FoundUserDto find(UserId userId);

    List<FoundUserDto> findAll();

}
