package com.kakao.cafe.qna.dto.response;

import com.kakao.cafe.qna.domain.Qna;
import com.kakao.cafe.qna.domain.Reply;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QnasResponse {

    private final List<QnaResponse> qnaResponses;

    public QnasResponse(List<QnaResponse> qnaResponses) {
        this.qnaResponses = qnaResponses;
    }

    public static QnasResponse qnasToResponse(List<Qna> qnas,
        Map<Long, List<Reply>> qnaIdToReplies) {
        List<QnaResponse> qnaResponses = new ArrayList<>();
        for (Qna qna : qnas) {
            qnaResponses.add(QnaResponse.qnaToResponse(qna, qnaIdToReplies.get(qna.getId())));
        }
        return new QnasResponse(qnaResponses);
    }

    public List<QnaResponse> getQnaResponses() {
        return qnaResponses;
    }
}
