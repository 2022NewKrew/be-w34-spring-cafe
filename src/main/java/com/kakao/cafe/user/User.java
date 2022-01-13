package com.kakao.cafe.user;

import lombok.Getter;
import lombok.Setter;

/**
 * 회원정보의 엔티티입니다.
 *
 * @author jm.hong
 */
@Getter @Setter
public class User {
    /**
     * 고유 ID 값입니다 [PK]
     */
    private Long id;
    /**
     * 회원 아이디입니다.
     */
    private String userId;
    /**
     * 회원의 비밀번호 입니다.
     */
    private String password;
    /**
     * 회원의 이름입니다.
     */
    private String name;
    /**
     * 회원의 이메일 주소입니다.
     */
    private String email;
    /**
     * 회원의 권한을 의미합니다.
     */
    private UserStatus role;
}
