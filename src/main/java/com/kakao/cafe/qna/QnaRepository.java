package com.kakao.cafe.qna;

import com.kakao.cafe.qna.domain.Qna;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class QnaRepository {

    List<Qna> qnas = Collections.synchronizedList(new ArrayList<>());

    public void save(Qna qna) {
        qnas.add(qna);
    }

    public List<Qna> findAll() {
        return new ArrayList<>(qnas);
    }

    public Qna findById(long id) {
        validateQna(id);
        return qnas.get((int) (id - 1L));
    }

    private void validateQna(long id) {
        if (id <= 0 || id > qnas.size()) {
            throw new IllegalArgumentException("존재하지 않는 qna 입니다.");
        }
    }
}
