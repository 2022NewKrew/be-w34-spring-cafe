package com.kakao.cafe.account.dto;

import com.kakao.cafe.account.entity.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class AccountDto {
    @NotBlank(message = "아이디는 필수 입력값 입니다.")
    private String userId;

    @NotBlank(message = "이름은 필수 입력값 입니다.")
    private String name;

//    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$", message = "영문, 특수문자, 숫자 포함 및 8자 이상이어야 합니다.")
    @NotNull(message = "패스워드는 필수 입력값 입니다.")
    @Size(min = 5, message = "패스워드는 5글자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력값 입니다.")
    @Email(message = "이메일 형식이 맞지 않습니다.")
    private String email;

    @Builder
    public AccountDto(String userId, String name, String password, String email) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Account toEntity(){
        return Account.builder()
                .userId(userId)
                .name(name)
                .password(password)
                .email(email).build();
    }

}
