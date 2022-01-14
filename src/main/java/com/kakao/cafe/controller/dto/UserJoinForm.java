package com.kakao.cafe.controller.dto;


import com.kakao.cafe.util.ValidInfo;
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

    @Size(max= ValidInfo.MAX_USER_ID_LEN, message=ValidInfo.USER_ID_MESSAGE)
    private String userId;

    @Size(max= ValidInfo.MAX_USER_NAME_LEN, message=ValidInfo.USER_NAME_MESSAGE)
    private String name;

    @Size(max= ValidInfo.MAX_USER_EMAIL_LEN, message=ValidInfo.USER_EMAIL_MESSAGE)
    private String email;

    @Size(max= ValidInfo.MAX_USER_PASSWORD_LEN, message = ValidInfo.USER_PASSWORD_MESSAGE)
    private String password;
}
