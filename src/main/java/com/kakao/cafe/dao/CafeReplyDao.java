package com.kakao.cafe.dao;

import com.kakao.cafe.model.Reply;

import java.util.List;

public interface CafeReplyDao {
    List<Reply> getReplyList(int postId);
    boolean submitReply(Reply reply);
    boolean deleteReply(String userId, int replyId);
}
