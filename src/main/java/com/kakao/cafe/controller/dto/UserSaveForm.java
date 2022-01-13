package com.kakao.cafe.controller.dto;

import lombok.Getter;
import lombok.Setter;

// 사용자로부터 수정사항 입력받는 dto
@Getter
@Setter
public class UserSaveForm {
    private String name;
    private String email;
    private String password;
}
