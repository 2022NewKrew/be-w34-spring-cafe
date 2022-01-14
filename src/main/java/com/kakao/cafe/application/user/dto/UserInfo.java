package com.kakao.cafe.application.user.dto;

import com.kakao.cafe.adapter.out.infra.persistence.user.UserVO;
import com.kakao.cafe.domain.user.User;
import java.util.Objects;

public class UserInfo {

    private final String userId;
    private final String name;
    private final String email;

    public UserInfo(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public static UserInfo from(User user) {
        return new UserInfo(user.getUserId(), user.getName(), user.getEmail());
    }

    public static UserInfo from(UserVO userVO) {
        return new UserInfo(userVO.getUserId(), userVO.getName(), userVO.getEmail());
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserInfo userInfo = (UserInfo) o;
        return userId.equals(userInfo.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
