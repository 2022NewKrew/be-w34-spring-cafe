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
import java.util.stream.Collectors;
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

        return QnasResponse.qnasToResponse(
            qnas,
            qnas.stream()
                .collect(
                    Collectors.toMap(Qna::getId,
                        qna -> replyRepository.findByQnaId(qna.getId())
                    )
                )
        );
    }

    public QnaResponse findById(long id) {
        Qna qna = qnaRepository.findById(id);
        return QnaResponse.qnaToResponse(
            qna,
            replyRepository.findByQnaId(qna.getId())
        );
    }

    public QnaResponse findByIdAndWriter(long id, String userId) {
        Qna qna = qnaRepository.findByIdAndWriter(id, userId);
        return QnaResponse.qnaToResponse(qna);
    }

    public void update(long id, QnaRequest qnaRequest) {
        qnaRepository.update(qnaRequest.toQna(id));
    }

    public void delete(long id, String userId) {
        qnaRepository.deleteByIdAndWriter(id, userId);
    }

    public void createReply(ReplyRequest replyRequest) {
        Reply reply = replyRequest.toReply();
        replyRepository.create(reply);
    }

    public void deleteReply(long replyId, String userId) {
        replyRepository.deleteByIdAndWriter(replyId, userId);
    }
}
