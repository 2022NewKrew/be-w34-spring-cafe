package com.kakao.cafe.domain.user.dto;

import com.kakao.cafe.core.ValidConst;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

// 사용자로부터 수정사항 입력받는 dto
@Getter
@Setter
public class UserUpdateForm {
    @Size(max= ValidConst.MAX_USER_ID_LEN, message= ValidConst.USER_ID_MESSAGE)
    private String userId;

    @Size(max= ValidConst.MAX_USER_NAME_LEN, message= ValidConst.USER_NAME_MESSAGE)
    private String name;

    @Size(max= ValidConst.MAX_USER_EMAIL_LEN, message= ValidConst.USER_EMAIL_MESSAGE)
    private String email;

    private String password;
}
