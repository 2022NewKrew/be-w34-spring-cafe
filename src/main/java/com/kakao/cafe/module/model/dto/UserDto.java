package com.kakao.cafe.module.model.dto;

import com.kakao.cafe.module.model.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private final Long sn;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public User toUser(Long id){
        return new User(id, userId, password, name, email);
    }

    public static UserDto toDto(User user){
        return new UserDto(user.getSn(), user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }
}
