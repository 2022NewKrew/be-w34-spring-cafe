package com.kakao.cafe.domain.user.dto;


import com.kakao.cafe.core.ValidConst;
import com.kakao.cafe.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinForm {

    @Size(max= ValidConst.MAX_USER_ID_LEN, message= ValidConst.USER_ID_MESSAGE)
    private String userId;

    @Size(max= ValidConst.MAX_USER_NAME_LEN, message= ValidConst.USER_NAME_MESSAGE)
    private String name;

    @Size(max= ValidConst.MAX_USER_EMAIL_LEN, message= ValidConst.USER_EMAIL_MESSAGE)
    private String email;

    @Size(max= ValidConst.MAX_USER_PASSWORD_LEN, message = ValidConst.USER_PASSWORD_MESSAGE)
    private String password;

    public User toUser() {
        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
