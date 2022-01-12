package com.kakao.cafe.Model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class UserDTO {
    private Integer userIdx;
    private LocalDate createdDate;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nickName;
    @NotBlank
    private String password;

    public UserDTO(String email, String nickname, String pwd) {
        this.createdDate = LocalDate.now();
        this.email = email;
        this.nickName = nickname;
        this.password = pwd;
    }
}
