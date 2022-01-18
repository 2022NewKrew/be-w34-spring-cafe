package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Qna;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QnaList {

    private final List<Qna> qnaList;

    private QnaList() {
        qnaList = new ArrayList<>();
    }

    public List<Qna> getQnaList() {
        return qnaList;
    }

    public void addQna(Qna qna) {
        qna.setIndex(qnaList.size() + 1);
        qnaList.add(qna);
    }

    public Qna findQnaByIndex(Integer index) {
        return qnaList.get(index - 1);
    }

    public void deleteByIndex(Integer index) {
        qnaList.removeIf(qna -> qna.getIndex().equals(index));
    }
}
