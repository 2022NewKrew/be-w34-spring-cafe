package com.kakao.cafe.qna;

import com.kakao.cafe.qna.domain.Qna;
import com.kakao.cafe.qna.domain.Reply;
import com.kakao.cafe.qna.dto.request.QnaRequest;
import com.kakao.cafe.qna.dto.request.ReplyRequest;
import com.kakao.cafe.qna.dto.response.QnaResponse;
import com.kakao.cafe.qna.dto.response.QnasResponse;
import com.kakao.cafe.qna.repository.QnaRepository;
import com.kakao.cafe.qna.repository.ReplyRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class QnaService {

    private final QnaRepository qnaRepository;
    private final ReplyRepository replyRepository;

    public QnaService(QnaRepository qnaRepository, ReplyRepository replyRepository) {
        this.qnaRepository = qnaRepository;
        this.replyRepository = replyRepository;
    }

    public void save(QnaRequest qnaRequest) {
        qnaRepository.save(qnaRequest.toQna());
    }

    public QnasResponse findAll() {
        List<Qna> qnas = qnaRepository.findAll();
        return QnasResponse.qnasToResponse(qnas);
    }

    public QnaResponse findById(long id) {
        Qna qna = qnaRepository.findById(id);
        return QnaResponse.qnaToResponse(qna);
    }

    public QnaResponse findById(long id, String userName) {
        Qna qna = qnaRepository.findById(id);
        qna.validateEqualsWriter(userName);
        return QnaResponse.qnaToResponse(qna);
    }

    public void update(long id, QnaRequest qnaRequest) {
        qnaRepository.update(qnaRequest.toQna(id));
    }

    public void delete(long id, String userId) {
        qnaRepository.delete(id, userId);
    }

    public void createReply(ReplyRequest replyRequest) {
        Reply reply = replyRequest.toReply();
        replyRepository.create(reply);
    }
}
