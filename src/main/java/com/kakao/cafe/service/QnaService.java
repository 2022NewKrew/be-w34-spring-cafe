package com.kakao.cafe.service;

import com.kakao.cafe.domain.Qna;
import com.kakao.cafe.dto.QnaDto;
import com.kakao.cafe.exception.QnaNotFoundException;
import com.kakao.cafe.repository.QnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QnaService {

    private final QnaRepository localQnaRepository;

    @Autowired
    public QnaService(QnaRepository qnaRepository) {
        this.localQnaRepository = qnaRepository;
    }

    public void createQna(QnaDto.CreateQnaRequest createQnaRequest) {
        Qna qna = createQnaRequest.toQnaEntity();
        localQnaRepository.save(qna);
    }

    public List<QnaDto.QnaResponse> findQnaList() {
        return localQnaRepository.findAll().stream()
                .map(QnaDto.QnaResponse::of)
                .collect(Collectors.toList());
    }

    public QnaDto.QnaResponse findQna(Integer index) {
        Qna qna = localQnaRepository.findByIndex(index)
                .orElseThrow(() -> new QnaNotFoundException("Qna Not Found (index: " + index + ")"));

        return QnaDto.QnaResponse.of(qna);
    }
}
