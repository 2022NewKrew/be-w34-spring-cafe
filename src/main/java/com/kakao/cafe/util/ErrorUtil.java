package com.kakao.cafe.util;

import com.kakao.cafe.vo.Reply;

import java.util.List;

public class ErrorUtil {

    private ErrorUtil() {}

    public static boolean checkSameString(String userId, String loginUserId){
        if(userId == null || loginUserId == null)
            return false;
        if(userId.equals(loginUserId))
            return true;
        return false;
    }

    public static boolean checkAllSameWriters(List<Reply> replies, String writer) {
        return replies.stream().filter(reply -> !reply.getWriter().equals(writer)).findFirst().isEmpty();
    }

}
