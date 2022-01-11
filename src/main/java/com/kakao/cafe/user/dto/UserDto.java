package com.kakao.cafe.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 회원에 리스트에 대한 DTO 입니다.
 *
 * @author jm.hong
 */
@Getter
@Setter
public class UserDto {
    private Long id;
    private String userId;
    private String name;
    private String email;
}
