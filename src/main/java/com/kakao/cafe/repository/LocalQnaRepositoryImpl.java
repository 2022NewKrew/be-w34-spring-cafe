package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Qna;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LocalQnaRepositoryImpl implements QnaRepository {

    private final QnaList qnaList;

    public LocalQnaRepositoryImpl(QnaList qnaList) {
        this.qnaList = qnaList;
    }

    @Override
    public void save(Qna qna) {
        qnaList.addQna(qna);
    }

    @Override
    public List<Qna> findAll() {
        return qnaList.getQnaList();
    }

    @Override
    public Optional<Qna> findByIndex(Integer index) {
        return Optional.of(qnaList.findQnaByIndex(index));
    }
}
