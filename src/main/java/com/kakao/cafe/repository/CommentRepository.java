package com.kakao.cafe.repository;

import com.kakao.cafe.dao.CommentDao;
import com.kakao.cafe.dao.PostDao;
import com.kakao.cafe.dao.UserDao;
import com.kakao.cafe.domain.comment.Comment;
import com.kakao.cafe.domain.comment.Comments;
import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.CommentDbDto;
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
        List<CommentDbDto> commentDtos = commentDao.findAll(postId);
        Post post = postDao.findById(postId);
        List<Comment> comments = commentDtos.stream()
                .map((commentDto) -> CommentDbMapper.toComment(commentDto, post, userDao.findById(commentDto.getUserId())))
                .collect(Collectors.toList());
        return new Comments(comments);
    }

    public Comment findById(long id) {
        CommentDbDto commentDbDto = commentDao.findById(id);
        Post post = postDao.findById(commentDbDto.getPostId());
        User user = userDao.findById(commentDbDto.getUserId());
        return CommentDbMapper.toComment(commentDbDto, post, user);
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

}
