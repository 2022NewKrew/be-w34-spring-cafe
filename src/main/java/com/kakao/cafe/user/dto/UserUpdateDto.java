package com.kakao.cafe.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 회원 정보 수정에대한 DTO 입니다.
 *
 * @author jm.hong
 */
@Getter
@Setter
public class UserUpdateDto {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
}
