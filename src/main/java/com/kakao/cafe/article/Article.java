package com.kakao.cafe.article;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public class Article {

    private long seq;

    private long userSeq;

    @NonNull
    private String writer;
    @NonNull
    private String title;
    @NonNull
    private String content;

    private LocalDateTime time;

    private boolean deleted;

    @Builder
    private Article(long seq, long userSeq, @NotNull String writer, @NotNull String title, @NotNull String content, LocalDateTime time, boolean deleted) {
        this.seq = seq;
        this.userSeq = userSeq;
        this.writer = writer;
        this.title = title;
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

    public @NotNull String getWriter() {
        return writer;
    }

    public @NotNull String getTitle() {
        return title;
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

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

}
