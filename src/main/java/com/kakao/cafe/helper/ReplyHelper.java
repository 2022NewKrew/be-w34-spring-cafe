package com.kakao.cafe.helper;

import com.kakao.cafe.model.Reply;

public class ReplyHelper {
    private static final String regexOfContent = "^[\\s\\S]{1,500}$";

    public static boolean checkRegexOfReply (Reply reply) {
        return checkRegexOfContent(reply.getContent());
    }

    private static boolean checkRegexOfContent(String content) {
        return content != null && content.matches(regexOfContent);
    }
}
