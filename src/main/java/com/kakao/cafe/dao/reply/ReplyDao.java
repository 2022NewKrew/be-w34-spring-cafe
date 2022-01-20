package com.kakao.cafe.dao.reply;

import com.kakao.cafe.model.reply.Reply;
import java.util.List;
import java.util.Optional;

public interface ReplyDao {

    Reply addReply(Reply reply);
    void updateReply(Reply reply);
    List<Reply> getReplies(int articleId);
    void deleteReply(int id);
    Optional<Reply> findReplyById(int replyId);
}
