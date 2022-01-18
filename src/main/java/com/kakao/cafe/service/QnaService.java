package com.kakao.cafe.service;

import com.kakao.cafe.domain.Qna;
import com.kakao.cafe.dto.QnaDto;
import com.kakao.cafe.exception.QnaNotFoundException;
import com.kakao.cafe.repository.QnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class QnaService {

    private final QnaRepository qnaRepository;

    @Autowired
    public QnaService(QnaRepository jdbcQnaRepositoryImpl) {
        this.qnaRepository = jdbcQnaRepositoryImpl;
    }

    @Transactional
    public void createQna(QnaDto.CreateQnaRequest createQnaRequest) {
        Qna qna = createQnaRequest.toQnaEntity();
        qnaRepository.save(qna);
    }

    public List<QnaDto.QnaResponse> findQnaList() {
        return qnaRepository.findAll().stream()
                .map(QnaDto.QnaResponse::of)
                .collect(Collectors.toList());
    }

    public QnaDto.QnaResponse findQna(Integer index) {
        Qna qna = qnaRepository.findByIndex(index)
                .orElseThrow(() -> new QnaNotFoundException(index));

        return QnaDto.QnaResponse.of(qna);
    }

    public QnaDto.QnaForUpdateReponse findQnaForUpdate(Integer index) {
        Qna qna = qnaRepository.findByIndex(index)
                .orElseThrow(() -> new QnaNotFoundException(index));

        return QnaDto.QnaForUpdateReponse.of(qna);
    }

    @Transactional
    public void updateQna(Integer index, QnaDto.UpdateQnaRequest updateQnaRequest) throws AccessDeniedException {
        Qna qna = qnaRepository.findByIndex(index)
                .orElseThrow(() -> new QnaNotFoundException(index));

        if (!qna.isValidUpdateUser(updateQnaRequest.getWriter())) {
            throw new AccessDeniedException("수정 권한이 없습니다");
        }

        Qna updatedQna = updateQnaRequest.toEntity(index);
        qna.updateQna(updatedQna);
        qnaRepository.save(qna);
    }

    public void validateUpdateUser(String writer, String userId) throws AccessDeniedException {
        if (!writer.equals(userId)) {
            throw new AccessDeniedException("수정 권한이 없습니다");
        }
    }
}
