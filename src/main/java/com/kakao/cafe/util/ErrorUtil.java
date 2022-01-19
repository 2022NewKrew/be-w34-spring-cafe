package com.kakao.cafe.util;

public class ErrorUtil {

    private ErrorUtil() {}

    public static boolean checkSameString(String userId, String loginUserId){
        if(userId == null || loginUserId == null)
            return false;
        if(userId.equals(loginUserId))
            return true;
        return false;
    }

}
