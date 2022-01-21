package com.kakao.cafe.repository;

import com.kakao.cafe.dao.CommentDao;
import com.kakao.cafe.dao.PostDao;
import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.domain.comment.Comment;
import com.kakao.cafe.domain.comment.Comments;
import com.kakao.cafe.dto.CommentPostUserDbDto;
import com.kakao.cafe.util.mapper.CommentDbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CommentRepository {

    private final CommentDao commentDao;
    private final UserDao userDao;
    private final PostDao postDao;

    @Autowired
    public CommentRepository(CommentDao commentDao, UserDao userDao, PostDao postDao) {
        this.commentDao = commentDao;
        this.userDao = userDao;
        this.postDao = postDao;
    }

    public Comments findAll(long postId) {
        List<CommentPostUserDbDto> commentPostUserDbDtos = commentDao.findAll(postId);
        List<Comment> comments = commentPostUserDbDtos.stream()
                .map(CommentDbMapper::toComment)
                .collect(Collectors.toList());
        return new Comments(comments);
    }

    public Comment findById(long id) {
        CommentPostUserDbDto commentPostUserDbDto = commentDao.findById(id);
        return CommentDbMapper.toComment(commentPostUserDbDto);
    }

    public int insert(Comment comment) {
        return commentDao.insert(CommentDbMapper.toDto(comment));
    }

    public int update(Comment comment) {
        return commentDao.update(CommentDbMapper.toDto(comment));
    }

    public int delete(long id) {
        return commentDao.deleteById(id);
    }

    public int deleteAll(long id) {
        return commentDao.deleteAll(id);
    }

}
