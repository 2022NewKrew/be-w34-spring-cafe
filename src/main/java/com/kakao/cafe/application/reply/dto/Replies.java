package com.kakao.cafe.application.reply.dto;

import com.kakao.cafe.domain.article.Reply;
import java.util.List;

public class Replies {

    private final List<Reply> replyList;

    public Replies(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public static Replies from(List<Reply> replyList) {
        return new Replies(replyList);
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

    public int getCountOfReplies() {
        return replyList.size();
    }
}
