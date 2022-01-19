package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;

public class Reply {

    private final int articleId;
    private final String userId;
    private final String writer;
    private final String contents;
    private final String createdAt;
    private int id;

    public Reply(Builder builder) {
        this.articleId = builder.articleId;
        this.userId = builder.userId;
        this.writer = builder.writer;
        this.contents = builder.contents;
        this.createdAt = builder.createdAt;
    }

    private static boolean checkBlankInString(String str) {
        return str.contains(" ");
    }

    private static boolean checkLengthOfString(String str) {
        return str.length() <= 0;
    }

    private static void checkUserId(String userId) throws IllegalUserIdException {
        if (checkLengthOfString(userId) || checkBlankInString(userId)) {
            throw new IllegalUserIdException("잘못된 ID 입니다.");
        }
    }

    private static void checkWriter(String writer) throws IllegalWriterException {
        if (checkLengthOfString(writer) || checkBlankInString(writer)) {
            throw new IllegalWriterException("작성자 이름이 잘못되었습니다.");
        }
    }

    private static void checkDate(String createdAt) throws IllegalDateException {
        if (checkLengthOfString(createdAt)) {
            throw new IllegalDateException("잘못된 날짜입니다.");
        }
    }

    public int getArticleId() {
        return articleId;
    }

    public String getUserId() {
        return userId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class Builder {

        private int articleId;
        private String userId;
        private String writer;
        private String contents;
        private String createdAt;

        public Reply build()
            throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException {
            checkUserId(userId);
            checkWriter(writer);
            checkDate(createdAt);
            return new Reply(this);
        }

        public Builder articleId(int articleId) {
            this.articleId = articleId;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder writer(String writer) {
            this.writer = writer;
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
    }
}
