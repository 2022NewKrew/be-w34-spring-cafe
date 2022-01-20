package com.kakao.cafe.dto.reply;

import com.kakao.cafe.domain.entity.Reply;
import com.kakao.cafe.repository.UserRepository;

import java.time.LocalDateTime;

public class ReplyWithWriterName {
    private final long replyId;
    private final long articleId;
    private final String writer;
    private final String writerId;
    private final String contents;
    private final LocalDateTime time;

    public ReplyWithWriterName(long replyId, long articleId, String writer, String writerId, String contents, LocalDateTime time) {
        this.replyId = replyId;
        this.articleId = articleId;
        this.writer = writer;
        this.writerId = writerId;
        this.contents = contents;
        this.time = time;
    }

    public ReplyWithWriterName(Reply reply, UserRepository userRepository) {
        this.replyId = reply.getReplyId();
        this.articleId = reply.getArticleId();
        this.writer = userRepository.search(reply.getWriterId()).getName();
        this.writerId = reply.getWriterId();
        this.contents = reply.getContents();
        this.time = reply.getTime();
    }

    public long getReplyId() {
        return replyId;
    }

    public long getArticleId() {
        return articleId;
    }

    public String getWriter() {
        return writer;
    }

    public String getWriterId() {
        return writerId;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
