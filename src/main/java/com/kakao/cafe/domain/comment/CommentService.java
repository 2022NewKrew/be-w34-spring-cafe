package com.kakao.cafe.domain.comment;

import com.kakao.cafe.domain.comment.dao.CommentDao;
import com.kakao.cafe.domain.comment.dto.CommentCreateDto;
import com.kakao.cafe.domain.comment.dto.CommentTableRowDto;
import com.kakao.cafe.domain.comment.exception.CommentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentDao commentDao;

    public void create(CommentCreateDto dto) {
        commentDao.save(dto.toEntity());
    }

    public void delete(Long id, Long userId) {
        Comment comment = commentDao.findById(id)
                .orElseThrow(CommentNotFoundException::new);
        comment.validateUserId(userId);
        commentDao.delete(comment);
    }

    public List<CommentTableRowDto> retrieveAllInArticle(Long articleId) {
        return commentDao.retrieveTableRowsByArticleId(articleId);
    }
}
