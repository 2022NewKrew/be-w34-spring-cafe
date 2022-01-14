package com.kakao.cafe.user;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원 등록 화면에서 데이터를 전달 받을 폼 객체
 *
 * Created by melodist
 * Date: 2022-01-10 010
 * Time: 오후 1:45
 */

@Data
@NoArgsConstructor
public class UserDto {

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    private String userId; // 사용자 아이디
    private String password; // 비밀번호
    private String name; // 이름
    private String email; // 이메일

    public User toEntity() {
        return new User(
                null,
                userId,
                password,
                name,
                email
        );
    }
}
