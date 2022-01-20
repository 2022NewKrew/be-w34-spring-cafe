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

    public List<Reply> getValue() {
        return replyList;
    }

    public boolean isEmpty() {
        return replyList.isEmpty();
    }

    public boolean containsReplyOf(String userId) {
        return replyList.stream().allMatch(r -> r.getUserId().equals(userId));
    }
}
