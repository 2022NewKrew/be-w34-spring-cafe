package com.kakao.cafe.application.user.dto;

import java.util.Collections;
import java.util.List;

public class UsersInfo {

    private final List<UserInfo> userList;

    public UsersInfo(List<UserInfo> userList) {
        this.userList = userList;
    }

    public static UsersInfo from(List<UserInfo> userInfoList) {
        return new UsersInfo(userInfoList);
    }

    public List<UserInfo> getUserList() {
        return Collections.unmodifiableList(userList);
    }
}
