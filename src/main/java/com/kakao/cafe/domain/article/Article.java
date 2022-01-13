package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalIdException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;

public class Article {

    private final int id;
    private final String writer;
    private final String title;
    private final String contents;
    private final String createdAt;

    public Article(int id, String writer, String title, String contents, String createdAt)
        throws IllegalIdException, IllegalWriterException, IllegalTitleException, IllegalDateException {
        checkId(id);
        checkWriter(writer);
        checkTitle(title);
        checkDate(createdAt);
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
    }

    public int getId() {
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

    public String getCreatedAt() {
        return createdAt;
    }

    private void checkId(int id) throws IllegalIdException {
        if (id <= 0) {
            throw new IllegalIdException("ID 값이 잘못되었습니다.");
        }
    }

    private boolean checkBlankInString(String str) {
        return str.contains(" ");
    }

    private boolean checkLengthOfString(String str) {
        return str.length() <= 0;
    }

    private void checkWriter(String writer) throws IllegalWriterException {
        if (checkLengthOfString(writer) || checkBlankInString(writer)) {
            throw new IllegalWriterException("작성자 이름이 잘못되었습니다.");
        }
    }

    private void checkTitle(String title) throws IllegalTitleException {
        if (checkLengthOfString(title)) {
            throw new IllegalTitleException("제목이 잘못되었습니다.");
        }
    }

    private void checkDate(String createdAt) throws IllegalDateException {
        if (checkLengthOfString(createdAt) || checkBlankInString(createdAt)) {
            throw new IllegalDateException("잘못된 날짜입니다.");
        }
    }
}
