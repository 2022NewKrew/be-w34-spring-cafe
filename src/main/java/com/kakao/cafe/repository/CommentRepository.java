package com.kakao.cafe.repository;

import com.kakao.cafe.dao.CommentDao;
import com.kakao.cafe.dao.CommentInsertDto;
import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.Comments;
import com.kakao.cafe.domain.Id;
import com.kakao.cafe.domain.UserId;
import com.kakao.cafe.dto.CommentDbDto;
import com.kakao.cafe.util.mapper.CommentDbMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {

    private final CommentDao commentDao;

    public CommentRepository(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public boolean create(Id questionId, UserId userId, Comment comment) {
        CommentInsertDto commentInsertDto = CommentDbMapper.toCommentInsertDto(questionId, userId, comment);
        int count = commentDao.insert(commentInsertDto);
        return count == 1;
    }

    public Comments findByArticleId(Id articleId) {
        List<CommentDbDto> commentDbDtos = commentDao.findByArticleId(articleId.getId());
        return CommentDbMapper.toComments(commentDbDtos);
    }

    public boolean delete(Id commentId, Id questionId, UserId userId) {
        int count = commentDao.delete(commentId.getId(), questionId.getId(), userId.getUserId());
        return count == 1;
    }
}
