package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.user.User;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
public class UserListDTO {

    private final List<UserDTO> userListDTO;

    public UserListDTO(List<User> UserList) {
        userListDTO = UserList.stream().map(UserDTO::newInstance).collect(Collectors.toUnmodifiableList());
    }

    public List<UserDTO> getCopiedUserList() {
        return new ArrayList<>(userListDTO);
    }
}
