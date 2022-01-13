package com.kakao.cafe.dao;

import com.kakao.cafe.domain.Qna;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class QnaDao {
    private long qnaId;
    private List<Qna> qnas;

    public QnaDao() {
        qnaId = 1l;
        qnas = new ArrayList<>();
    }

    public void createQna(Qna qna) {
        final Qna newQna = Qna.from(qna);
        newQna.setQnaId(qnaId++);
        newQna.setViews(0);
        qnas.add(newQna);
    }

    public List<Qna> getQnas() {
        return qnas;
    }

    public Qna getQna(long qnaId) {
        addViews(qnaId);

        return qnas.stream()
                .filter(qna -> qna.getQnaId() == qnaId)
                .findFirst()
                .orElse(null);
    }

    private void addViews(long qnaId) {
        qnas.stream()
                .filter(qna -> qna.getQnaId() == qnaId)
                .findFirst()
                .ifPresent(qna -> qna.setViews(qna.getViews() + 1));
    }
}
