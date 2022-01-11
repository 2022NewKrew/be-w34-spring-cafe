package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Qna;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class QnaList {

    private final ArrayList<Qna> qnaList;

    private QnaList() {
        qnaList = new ArrayList<>();
    }

    public ArrayList<Qna> getQnaList() {
        return qnaList;
    }

    public void addQna(Qna qna) {
        qna.setIndex(qnaList.size() + 1);
        qnaList.add(qna);
    }

    public Qna findQnaByIndex(Integer index) {
        return qnaList.get(index - 1);
    }
}
