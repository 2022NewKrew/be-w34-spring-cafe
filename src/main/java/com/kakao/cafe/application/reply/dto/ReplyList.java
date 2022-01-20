package com.kakao.cafe.application.reply.dto;

import com.kakao.cafe.domain.article.Reply;
import java.util.List;

public class ReplyList {

    private final List<Reply> replyList;

    public ReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public static ReplyList from(List<Reply> replyList) {
        return new ReplyList(replyList);
    }

    public List<Reply> getReplyList() {
        return replyList;
    }
}
