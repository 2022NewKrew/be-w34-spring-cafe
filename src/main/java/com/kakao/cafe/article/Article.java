package com.kakao.cafe.article;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public class Article {

    private long seq;
    @NonNull
    private String writer;
    @NonNull
    private String title;
    @NonNull
    private String content;

    private LocalDateTime time;

    @Builder
    private Article(long seq, @NotNull String writer, @NotNull String title, @NotNull String content, LocalDateTime time) {
        this.seq = seq;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public long getSeq() {
        return seq;
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

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

}
