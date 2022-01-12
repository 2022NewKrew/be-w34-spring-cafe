package com.kakao.cafe.qna.dto.request;

import com.kakao.cafe.qna.domain.Qna;

public class QnaRequest {

    private final String writer;
    private final String title;
    private final String contents;

    public QnaRequest(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Qna toQna() {
        return new Qna(writer, title, contents);
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
}
