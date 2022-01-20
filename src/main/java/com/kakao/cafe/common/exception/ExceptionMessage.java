package com.kakao.cafe.common.exception;

public class ExceptionMessage {

    public static String INVALID_EMAIL_FORM_EXCEPTION = "유효하지 않은 이메일 형식입니다.";
    public static String USER_ID_DUPLICATION_EXCEPTION = "이미 존재하는 아이디입니다.";
    public static String VALUE_LENGTH_LOWERBOUND_EXCEPTION = "길이가 0보다 커야합니다.";
    public static String NOT_MATCHING_PASSWORD_EXCEPTION = "비밀번호가 일치하지 않습니다.";
    public static String NO_SUCH_USER_EXCEPTION = "id에 해당하는 User가 존재하지 않습니다.";
    public static String REQUIRED_RE_LOGIN_EXCEPTION = "인증정보가 유효하지 않습니다. 다시 로그인해주세요.";
}
