package com.kakao.cafe.service;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.Qna;
import com.kakao.cafe.dto.CommentDto;
import com.kakao.cafe.dto.QnaDto;
import com.kakao.cafe.exception.QnaNotFoundException;
import com.kakao.cafe.repository.CommentRepository;
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
    private final CommentRepository commentRepository;

    @Autowired
    public QnaService(QnaRepository jdbcQnaRepositoryImpl, CommentRepository jdbcCommentRepositoryImpl) {
        this.qnaRepository = jdbcQnaRepositoryImpl;
        this.commentRepository = jdbcCommentRepositoryImpl;
    }

    @Transactional
    public void createQna(QnaDto.CreateQnaRequest createQnaRequest) {
        Qna qna = createQnaRequest.toQnaEntity();
        qnaRepository.save(qna);
    }

    public List<QnaDto.QnaResponse> findQnaList() {
        return qnaRepository.findAllByDeleted(false).stream()
                .map(QnaDto.QnaResponse::of)
                .collect(Collectors.toList());
    }

    public QnaDto.QnaResponse findQna(Integer index) {
        Qna qna = qnaRepository.findByIndex(index)
                .orElseThrow(() -> new QnaNotFoundException(index));

        List<Comment> comments = commentRepository.findByQnaIndexAndDeleted(index, false);

        List<CommentDto.ReadCommentResponse> commentResponses = comments.stream()
                .map(CommentDto.ReadCommentResponse::of)
                .collect(Collectors.toList());

        return QnaDto.QnaResponse.of(qna, commentResponses);
    }

    public QnaDto.QnaForUpdateResponse findQnaForUpdate(Integer index, String userId) throws AccessDeniedException {
        Qna qna = qnaRepository.findByIndex(index)
                .orElseThrow(() -> new QnaNotFoundException(index));

        if (!qna.isValidUpdateUser(userId)) {
            throw new AccessDeniedException("수정 권한이 없습니다");
        }

        return QnaDto.QnaForUpdateResponse.of(qna);
    }

    @Transactional
    public void updateQna(Integer index, QnaDto.UpdateQnaRequest updateQnaRequest, String userId) throws AccessDeniedException {
        Qna qna = qnaRepository.findByIndex(index)
                .orElseThrow(() -> new QnaNotFoundException(index));

        if (!qna.isValidUpdateUser(userId)) {
            throw new AccessDeniedException("수정 권한이 없습니다");
        }

        qna.updateQna(updateQnaRequest.getTitle(), updateQnaRequest.getContents());
        qnaRepository.save(qna);
    }

    @Transactional
    public void deleteQna(Integer index, String userId) throws AccessDeniedException {
        Qna qna = qnaRepository.findByIndex(index)
                .orElseThrow(() -> new QnaNotFoundException(index));

        if (!qna.isValidDeleteUser(userId)) {
            throw new AccessDeniedException("해당 글을 삭제할 권한이 없습니다");
        }

        List<Comment> comments = commentRepository.findByQnaIndexAndDeleted(index, false);

        boolean isDeletedComments = comments.stream()
                .allMatch(comment -> comment.isValidDeleteUser(userId));

        if (!isDeletedComments) {
            throw new AccessDeniedException("해당 글의 댓글을 삭제할 권한이 없습니다.");
        }

        comments.forEach(Comment::delete);
        commentRepository.batchUpdate(comments);

        qna.delete();
        qnaRepository.save(qna);
    }
}
