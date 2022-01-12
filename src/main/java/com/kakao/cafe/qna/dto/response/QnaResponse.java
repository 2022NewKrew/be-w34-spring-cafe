package com.kakao.cafe.qna.dto.response;

import com.kakao.cafe.qna.domain.Qna;
import java.time.format.DateTimeFormatter;

public class QnaResponse {

    private static final DateTimeFormatter
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");

    private final Long id;
    private final String writer;
    private final String title;
    private final String contents;
    private final String createTime;

    public QnaResponse(Long id, String writer, String title, String contents, String createTime) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createTime = createTime;
    }

    public static QnaResponse qnaToResponse(Long id, Qna qna) {
        return new QnaResponse(id,
            qna.getWriter(),
            qna.getTitle(),
            qna.getContents(),
            qna.getCreateTime().format(dateTimeFormatter));
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

    public String getCreateTime() {
        return createTime;
    }
}
