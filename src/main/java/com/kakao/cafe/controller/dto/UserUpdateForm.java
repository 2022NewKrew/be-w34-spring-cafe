package com.kakao.cafe.controller.dto;

import com.kakao.cafe.util.ValidInfo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

// 사용자로부터 수정사항 입력받는 dto
@Getter
@Setter
public class UserUpdateForm {
    @Size(max= ValidInfo.MAX_USER_NAME_LEN, message=ValidInfo.USER_NAME_MESSAGE)
    private String name;

    @Size(max= ValidInfo.MAX_USER_EMAIL_LEN, message=ValidInfo.USER_EMAIL_MESSAGE)
    private String email;

    private String password;
}
