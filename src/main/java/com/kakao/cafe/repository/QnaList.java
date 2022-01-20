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
        qna.setId(qnaList.size() + 1);
        qnaList.add(qna);
    }

    public Qna findQnaById(Integer id) {
        return qnaList.get(id - 1);
    }

    public void deleteById(Integer id) {
        qnaList.removeIf(qna -> qna.getId().equals(id));
    }
}
