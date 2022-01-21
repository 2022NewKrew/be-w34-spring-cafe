package com.kakao.cafe.service;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.Qna;
import com.kakao.cafe.dto.QnaDto;
import com.kakao.cafe.exception.QnaNotFoundException;
import com.kakao.cafe.repository.CommentRepository;
import com.kakao.cafe.repository.QnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<QnaDto.QnaResponse> findQnaListWithPaging(Pageable page) {
        return qnaRepository.findAllByDeleted(false, page)
                .map(QnaDto.QnaResponse::of);
    }

    public QnaDto.QnaResponse findQna(Integer qnaId) {
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new QnaNotFoundException(qnaId));

        List<Comment> comments = commentRepository.findByQnaIdAndDeleted(qnaId, false);

        return QnaDto.QnaResponse.of(qna, comments);
    }

    public QnaDto.QnaForUpdateResponse findQnaForUpdate(Integer qnaId, String userId) throws AccessDeniedException {
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new QnaNotFoundException(qnaId));

        if (!qna.isValidUpdateUser(userId)) {
            throw new AccessDeniedException("수정 권한이 없습니다");
        }

        return QnaDto.QnaForUpdateResponse.of(qna);
    }

    @Transactional
    public void updateQna(Integer qnaId, QnaDto.UpdateQnaRequest updateQnaRequest, String userId) throws AccessDeniedException {
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new QnaNotFoundException(qnaId));

        if (!qna.isValidUpdateUser(userId)) {
            throw new AccessDeniedException("수정 권한이 없습니다");
        }

        qna.updateQna(updateQnaRequest.getTitle(), updateQnaRequest.getContents());
        qnaRepository.save(qna);
    }

    @Transactional
    public void deleteQna(Integer qnaId, String userId) throws AccessDeniedException {
        Qna qna = qnaRepository.findById(qnaId)
                .orElseThrow(() -> new QnaNotFoundException(qnaId));

        if (!qna.isValidDeleteUser(userId)) {
            throw new AccessDeniedException("해당 글을 삭제할 권한이 없습니다");
        }

        List<Comment> comments = commentRepository.findByQnaIdAndDeleted(qnaId, false);

        boolean hasAuthForDeleteAllComments = comments.stream()
                .allMatch(comment -> comment.isValidDeleteUser(userId));

        if (!hasAuthForDeleteAllComments) {
            throw new AccessDeniedException("해당 글의 댓글을 삭제할 권한이 없습니다.");
        }

        comments.forEach(Comment::delete);
        commentRepository.batchUpdate(comments);

        qna.delete();
        qnaRepository.save(qna);
    }
}
