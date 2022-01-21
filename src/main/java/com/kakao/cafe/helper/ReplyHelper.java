package com.kakao.cafe.helper;

import com.kakao.cafe.model.Reply;
import com.kakao.cafe.vo.ReplyVo;

import java.util.List;
import java.util.stream.Collectors;

public class ReplyHelper {
    private static final String regexOfContent = "^[\\s\\S]{1,500}$";

    public static boolean checkRegexOfReply (Reply reply) {
        return checkRegexOfContent(reply.getContent());
    }

    private static boolean checkRegexOfContent(String content) {
        return content != null && content.matches(regexOfContent);
    }

    public static List<ReplyVo> convertModelToVo (List<Reply> replyList, String loginUser) {
        return replyList.stream().map(reply -> new ReplyVo(reply, loginUser)).collect(Collectors.toList());
    }
}
