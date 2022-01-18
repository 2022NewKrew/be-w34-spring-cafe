package com.kakao.cafe.user.application.port.in;

import java.util.List;

public interface UserCommonQueryUseCase {
    List<UserInventoryInfo> findUserInventoryInfo();
}
