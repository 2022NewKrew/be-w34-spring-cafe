package com.kakao.cafe.qna.dto.response;

import com.kakao.cafe.qna.domain.Qna;
import java.util.ArrayList;
import java.util.List;

public class QnasResponse {

    private final List<QnaResponse> qnaResponses;

    public QnasResponse(List<QnaResponse> qnaResponses) {
        this.qnaResponses = qnaResponses;
    }

    public static QnasResponse qnasToResponse(List<Qna> qnas) {
        List<QnaResponse> qnaResponses = new ArrayList<>();
        long i = 1L;
        for (Qna qna : qnas) {
            qnaResponses.add(QnaResponse.qnaToResponse(i++, qna));
        }
        return new QnasResponse(qnaResponses);
    }

    public List<QnaResponse> getQnaResponses() {
        return qnaResponses;
    }
}
