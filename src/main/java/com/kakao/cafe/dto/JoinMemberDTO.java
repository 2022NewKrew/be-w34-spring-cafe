package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class JoinMemberDTO {

    private Long memberId;
    @NotNull
    private String userId;
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String email;
}
