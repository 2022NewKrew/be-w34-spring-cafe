package com.kakao.cafe.service;

import com.kakao.cafe.dao.QnaDao;
import com.kakao.cafe.domain.Qna;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaService {
    private final QnaDao qnaDao;

    public QnaService(QnaDao qnaDao) {
        this.qnaDao =qnaDao;
    }

    public void save(Qna qna) {
        qnaDao.save(qna);
    }

    public List<Qna> findAll() {
        return qnaDao.findAll();
    }

    public Qna findByQnaId(long qnaId) {
        qnaDao.updateViews(qnaId);
        return qnaDao.findByQnaId(qnaId);
    }

    public void update(Qna qna) {
        qnaDao.update(qna);
    }

    public void delete(long qnaId) {
        qnaDao.deleteByQnaId(qnaId);
    }
}
