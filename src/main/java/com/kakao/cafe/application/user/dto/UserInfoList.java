package com.kakao.cafe.application.user.dto;

import java.util.Collections;
import java.util.List;

public class UserInfoList {

    private final List<UserInfo> userList;

    public UserInfoList(List<UserInfo> userList) {
        this.userList = userList;
    }

    public static UserInfoList from(List<UserInfo> userInfoList) {
        return new UserInfoList(userInfoList);
    }

    public List<UserInfo> getUserList() {
        return Collections.unmodifiableList(userList);
    }
}
