package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.user.User;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UserDTO {

    private final long id;
    private final String userId;
    private final String email;
    private final String registerDate;

    public UserDTO(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.registerDate = user.getRegisterDate();
    }
}
