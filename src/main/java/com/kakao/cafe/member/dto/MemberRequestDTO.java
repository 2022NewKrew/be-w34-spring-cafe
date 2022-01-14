package com.kakao.cafe.member.dto;

import com.kakao.cafe.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString
public class MemberRequestDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String nickname;

    @NotEmpty
    private String password;

    @NotEmpty
    private String passwordCheck;

    public Member toMember(LocalDate createDate) {
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .createDate(createDate)
                .build();
    }
}
