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
    private final LocalDateTime createDate;

    public Article(int id, String title, String writer, String contents, LocalDateTime createDate) {
        checkTitle(title);
        checkWriter(writer);
        checkContents(contents);

        this.id = id;
        this.title = title;
        this.writer = writer;
        this.contents = contents;
        this.createDate = createDate;
    }

    public Article(int id, String title, String writer, String contents) {
        this(id, title, writer, contents, LocalDateTime.now());
    }

    private void checkTitle(String title) {
        NullChecker.checkNotNull(title);

        if (title.length() > ALLOWED_LENGTH_TITLE) {
            throw new IllegalArgumentException(String.format("제목의 길이는 %s 이하여야 합니다.", ALLOWED_LENGTH_TITLE));
        }
    }

    private void checkWriter(String writer) {
        NullChecker.checkNotNull(writer);

        if (writer.length() > ALLOWED_LENGTH_WRITER) {
            throw new IllegalArgumentException(String.format("작성자 이름의 길이는 %s 이하여야 합니다.", ALLOWED_LENGTH_WRITER));
        }
    }

    private void checkContents(String contents) {
        NullChecker.checkNotNull(contents);

        if (contents.length() > ALLOWED_LENGTH_CONTENTS) {
            throw new IllegalArgumentException(String.format("본문의 길이는 %s 이하여야 합니다.", ALLOWED_LENGTH_CONTENTS));
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
}
