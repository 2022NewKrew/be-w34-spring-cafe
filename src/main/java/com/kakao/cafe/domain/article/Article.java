package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;

public class Article {

    private final String writer;
    private final String title;
    private final String contents;
    private final String createdAt;
    private int id;

    public Article(Builder builder) {
        this.writer = builder.writer;
        this.title = builder.title;
        this.contents = builder.contents;
        this.createdAt = builder.createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public static class Builder {

        private String writer;
        private String title;
        private String contents;
        private String createdAt;

        public Builder writer(String writer) {
            this.writer = writer;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder contents(String contents) {
            this.contents = contents;
            return this;
        }

        public Builder createdAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Article build()
            throws IllegalWriterException, IllegalTitleException, IllegalDateException {
            checkWriter(writer);
            checkTitle(title);
            checkDate(createdAt);
            return new Article(this);
        }
    }

    private static boolean checkBlankInString(String str) {
        return str.contains(" ");
    }

    private static boolean checkLengthOfString(String str) {
        return str.length() <= 0;
    }

    private static void checkWriter(String writer) throws IllegalWriterException {
        if (checkLengthOfString(writer) || checkBlankInString(writer)) {
            throw new IllegalWriterException("작성자 이름이 잘못되었습니다.");
        }
    }

    private static void checkTitle(String title) throws IllegalTitleException {
        if (checkLengthOfString(title)) {
            throw new IllegalTitleException("제목이 잘못되었습니다.");
        }
    }

    private static void checkDate(String createdAt) throws IllegalDateException {
        if (checkLengthOfString(createdAt)) {
            throw new IllegalDateException("잘못된 날짜입니다.");
        }
    }
}
