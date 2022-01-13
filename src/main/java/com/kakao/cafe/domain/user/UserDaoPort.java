package com.kakao.cafe.domain.user;

import java.util.Map;

public interface UserDaoPort {
    Map<String, User> getUsers();
}
