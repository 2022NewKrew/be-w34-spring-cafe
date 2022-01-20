package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Qna;

import java.util.List;
import java.util.Optional;

public interface QnaRepository {
    void save(Qna qna);

    Optional<Qna> findById(Integer id);

    List<Qna> findAllByDeleted(Boolean deleted);
}
