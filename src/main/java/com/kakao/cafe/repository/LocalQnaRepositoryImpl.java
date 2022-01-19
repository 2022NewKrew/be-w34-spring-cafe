package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Qna;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Qna> findAllByDeleted(Boolean deleted) {
        return qnaList.getQnaList().stream()
                .filter(qna -> qna.getDeleted().equals(deleted))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Qna> findByIndex(Integer index) {
        return Optional.of(qnaList.findQnaByIndex(index));
    }
}
