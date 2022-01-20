package com.kakao.cafe.qna.repository;

import com.kakao.cafe.qna.domain.Qna;
import java.util.List;

public interface QnaRepository {

    void save(Qna qna);

    List<Qna> findAll();

    Qna findById(long id);

    void update(Qna qna);

    void delete(long id, String userId);

    Qna findByIdAndWriter(long id, String userName);
}
