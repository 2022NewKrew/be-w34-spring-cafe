package com.kakao.cafe.dto;

import com.kakao.cafe.model.User;
import com.kakao.cafe.utils.EncryptionUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private Integer id;
    @NotBlank(message = "id를 입력해주세요")
    private String userId;
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
    @NotBlank(message = "이름을 입력해주세요")
    private String userName;
    @NotBlank(message = "이메일을 입력해주세요")
    private String email;


    public User toEntity() {
        return User.builder().user_id(0)
                .userId(userId)
                .password(EncryptionUtils.encryptMD5(password))
                .userName(userName)
                .email(email)
                .build();
    }

}
