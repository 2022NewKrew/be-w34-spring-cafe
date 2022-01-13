package com.kakao.cafe.model;

import com.kakao.cafe.utility.NullChecker;

import java.time.LocalDateTime;

public class Article {
    private static final int ALLOWED_LENGTH_TITLE = 16;
    private static final int ALLOWED_LENGTH_WRITER = 8;
    private static final int ALLOWED_LENGTH_CONTENTS = 100;

    private final int id;
    private final String title;
    private final String writer;
    private final String contents;
    private LocalDateTime createDate;

    public Article(int id, String title, String writer, String contents) {
        checkTitle(title);
        checkWriter(writer);
        checkContents(contents);

        this.id = id;
        this.title = title;
        this.writer = writer;
        this.createDate = LocalDateTime.now();
        this.contents = contents;
    }

    private void checkTitle(String title) {
        NullChecker.checkNotNull(title);

        if (title.length() > ALLOWED_LENGTH_TITLE) {
            throw new IllegalArgumentException("제목의 길이가 너무 깁니다.");
        }
    }

    private void checkWriter(String writer) {
        NullChecker.checkNotNull(writer);

        if (writer.length() > ALLOWED_LENGTH_WRITER) {
            throw new IllegalArgumentException("작성자 이름의 길이가 너무 깁니다.");
        }
    }

    private void checkContents(String contents) {
        NullChecker.checkNotNull(contents);

        if (contents.length() > ALLOWED_LENGTH_CONTENTS) {
            throw new IllegalArgumentException("본문의 길이가 너무 깁니다.");
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public String getContents() {
        return contents;
    }

    public void setCreateDate(LocalDateTime localDateTime) {
        this.createDate = localDateTime;
    }
}
