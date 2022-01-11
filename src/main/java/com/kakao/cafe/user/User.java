package com.kakao.cafe.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by melodist
 * Date: 2022-01-10 010
 * Time: 오후 1:38
 */
@Getter
@AllArgsConstructor
public class User {
    
    private String userId; // 사용자 아이디
    private String password; // 비밀번호
    private String name; // 이름
    private String email; // 이메일
}
