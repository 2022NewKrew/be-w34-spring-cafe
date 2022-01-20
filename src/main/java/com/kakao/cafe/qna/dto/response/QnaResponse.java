package com.kakao.cafe.qna.dto.response;

import com.kakao.cafe.qna.domain.Qna;
import com.kakao.cafe.qna.domain.Reply;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class QnaResponse {

    private static final DateTimeFormatter
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");

    private final Long id;
    private final String writer;
    private final String title;
    private final String contents;
    private final String createTime;
    private final List<ReplyResponse> replies;

    public QnaResponse(Long id, String writer, String title, String contents, String createTime,
        List<ReplyResponse> replies) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createTime = createTime;
        this.replies = replies;
    }

    public static QnaResponse qnaToResponse(Qna qna) {
        return qnaToResponse(qna, new ArrayList<>());
    }

    public static QnaResponse qnaToResponse(Qna qna, List<Reply> replies) {

        return new QnaResponse(
            qna.getId(),
            qna.getWriter(),
            qna.getTitle(),
            qna.getContents(),
            qna.getCreateTime().format(dateTimeFormatter),
            ReplyResponse.toResponses(replies)
        );
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

    public List<ReplyResponse> getReplies() {
        return replies;
    }
}
