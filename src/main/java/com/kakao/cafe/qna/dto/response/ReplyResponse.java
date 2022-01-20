package com.kakao.cafe.qna.dto.response;

import com.kakao.cafe.qna.domain.Reply;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReplyResponse {

    private final long id;
    private final long qnaId;
    private final String writer;
    private final String contents;
    private final LocalDateTime createTime;

    public ReplyResponse(long id, long qnaId, String writer, String contents,
        LocalDateTime createTime) {
        this.id = id;
        this.qnaId = qnaId;
        this.writer = writer;
        this.contents = contents;
        this.createTime = createTime;
    }

    public static ReplyResponse toResponse(Reply reply) {
        return new ReplyResponse(
            reply.getId(),
            reply.getQnaId(),
            reply.getWriter(),
            reply.getContents(),
            reply.getCreateTime()
        );
    }

    public static List<ReplyResponse> toResponses(List<Reply> replies) {
        return replies.stream()
            .map(ReplyResponse::toResponse)
            .collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public long getQnaId() {
        return qnaId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }
}
