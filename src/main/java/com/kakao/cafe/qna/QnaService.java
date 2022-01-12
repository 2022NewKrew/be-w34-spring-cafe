package com.kakao.cafe.qna;

import com.kakao.cafe.qna.domain.Qna;
import com.kakao.cafe.qna.dto.request.QnaRequest;
import com.kakao.cafe.qna.dto.response.QnasResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class QnaService {

    private final QnaRepository qnaRepository;

    public QnaService(QnaRepository qnaRepository) {
        this.qnaRepository = qnaRepository;
    }

    public void save(QnaRequest qnaRequest) {
        qnaRepository.save(qnaRequest.toQna());
    }

    public QnasResponse findAll() {
        List<Qna> qnas = qnaRepository.findAll();
        return QnasResponse.qnasToResponse(qnas);
    }
}
