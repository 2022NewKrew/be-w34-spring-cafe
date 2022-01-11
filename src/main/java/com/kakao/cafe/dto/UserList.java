package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UserList {
    private List<UserListElement> userList;
}
