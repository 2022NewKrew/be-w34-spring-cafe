package com.kakao.cafe.dto;

import java.util.List;

public class UserListDto {
    private int total;
    private List<UserDto> userList;

    public UserListDto(int total, List<UserDto> userList){
        this.total = total;
        this.userList = userList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<UserDto> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDto> userList) {
        this.userList = userList;
    }
}
