package com.kakao.cafe.service;

import com.kakao.cafe.dao.QnaDao;
import com.kakao.cafe.domain.Qna;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaService {
    private final QnaDao qnaDao;

    public QnaService() {
        qnaDao = new QnaDao();
    }

    public void createQna(Qna qna) {
        qnaDao.createQna(qna);
    }

    public List<Qna> getQnas() {
        return qnaDao.getQnas();
    }

    public Qna getQna(long qnaId) {
        return qnaDao.getQna(qnaId);
    }
}
