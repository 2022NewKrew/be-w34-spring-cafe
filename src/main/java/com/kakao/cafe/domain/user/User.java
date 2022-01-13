package com.kakao.cafe.domain.user;

import com.kakao.cafe.web.dto.user.UsersSaveRequestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String accId;   // accountId
    private String accPw;   // accountPassword
    private String name;
    private String email;

    public User(int id, UsersSaveRequestDto userDto){
        this.id = id;
        this.accId = userDto.getAccId();
        this.accPw = userDto.getAccPw();
        this.name = userDto.getName();
        this.email = userDto.getEmail();
    }
}
