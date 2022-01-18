package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.exception.IllegalUserIdFormatException;
import com.kakao.cafe.domain.user.exception.IllegalUserIdLengthException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserId {
    private static final String USERID_LENGTH_EXCEPTION = "아이디는 4에서 30글자 사이여야 합니다..";
    private static final String USERID_FORMAT_EXCEPTION = "영문소문자, 숫자, 마침표만 사용할 수 있습니다.";
    private static final String REGEX_USERID_FORMAT = "^[a-z0-9.]+$";
    private static final int MIN_LENGTH_USERID = 4;
    private static final int MAX_LENGTH_USERID = 30;

    private final String userId;

    public UserId(String userId) {
        validateUserId(userId);
        this.userId = userId;
    }

    private void validateUserId(String userId) {
        validateUserIdLength(userId);
        validateUserIdFormat(userId);
    }

    private void validateUserIdLength(String userId) {
        if(userId == null || userId.length() < MIN_LENGTH_USERID || userId.length() > MAX_LENGTH_USERID) {
            throw new IllegalUserIdLengthException(USERID_LENGTH_EXCEPTION);
        }
    }

    private void validateUserIdFormat(String userId) {
        Pattern p = Pattern.compile(REGEX_USERID_FORMAT);
        Matcher m = p.matcher(userId);
        if(!m.matches()) {
            throw new IllegalUserIdFormatException(USERID_FORMAT_EXCEPTION);
        }
    }

    public String getValue() {
        return userId;
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof UserId)) {
            return false;
        }

        UserId other = (UserId) obj;
        return userId.equals(other.getValue());
    }

    @Override
    public String toString() {
        return userId;
    }
}
