package com.kakao.cafe.qna.domain;

import com.kakao.cafe.user.exception.CustomInvalidedSessionException;
import java.time.LocalDateTime;

public class Qna {

    private final Long id;
    private final String writer;
    private final String title;
    private final String contents;
    private final LocalDateTime createTime;

    public Qna(String writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Qna(Long id, String writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void validateEqualsWriter(String userName) {
        if (!writer.equals(userName)) {
            throw new CustomInvalidedSessionException();
        }
    }
}
