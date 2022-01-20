package com.kakao.cafe.reply;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public class Reply {

    private long seq;

    private long userSeq;

    private long articleSeq;

    @NonNull
    private String writer;
    @NonNull
    private String content;

    private LocalDateTime time;

    private boolean deleted;

    @Builder
    private Reply(long seq, long userSeq, long articleSeq, @NotNull String writer, @NotNull String content, LocalDateTime time, boolean deleted) {
        this.seq = seq;
        this.userSeq = userSeq;
        this.articleSeq = articleSeq;
        this.writer = writer;
        this.content = content;
        this.time = time;
        this.deleted = deleted;
    }

    public long getSeq() {
        return seq;
    }

    public long getUserSeq() {
        return userSeq;
    }

    public long getArticleSeq() {
        return articleSeq;
    }

    public @NotNull String getWriter() {
        return writer;
    }

    public @NotNull String getContent() {
        return content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public boolean isDeleted() {
        return deleted;
    }

}
