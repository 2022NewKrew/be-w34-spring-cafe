package com.kakao.cafe.user;

import lombok.Data;

/**
 * 회원 등록 화면에서 데이터를 전달 받을 폼 객체
 *
 * Created by melodist
 * Date: 2022-01-10 010
 * Time: 오후 1:45
 */

@Data
public class UserForm {

    private String userId; // 사용자 아이디
    private String password; // 비밀번호
    private String name; // 이름
    private String email; // 이메일
}
