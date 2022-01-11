package com.kakao.cafe.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserList {
    private List<UserDTO> userList;
    private Integer totalCount;

    public UserList() {
        this.userList = new ArrayList<>();
        this.totalCount = 0;
    }

    public void add(UserDTO userDTO){
        userDTO.setUserIdx(++this.totalCount);
        this.userList.add(userDTO);
    }

}
