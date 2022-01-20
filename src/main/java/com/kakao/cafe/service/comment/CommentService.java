package com.kakao.cafe.service.comment;

import com.kakao.cafe.domain.comment.Comment;
import com.kakao.cafe.domain.comment.CommentRepository;
import com.kakao.cafe.exception.NoAuthorizationException;
import com.kakao.cafe.model.comment.CommentDto;
import com.kakao.cafe.model.comment.CommentWriteRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public void saveComment(long postId, long writerId, CommentWriteRequest request) {
        try {
            commentRepository.save(Comment.builder()
                    .postId(postId)
                    .writerId(writerId)
                    .content(request.getContent())
                    .createdAt(LocalDateTime.now())
                    .build());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("댓글을 등록하는 과정에서 문제가 발생하였습니다.");
        }
    }

    public List<CommentDto> getCommentsByPostId(long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    public void deleteById(long id, long postId, long writerId) {
        Comment comment = getCommentById(id);
        validateCommentPostId(comment.getPostId(), postId);
        validateCommentWriterId(comment.getWriterId(), writerId);

        try {
            commentRepository.deleteById(id);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("댓글을 삭제하는 과정에서 문제가 발생하였습니다.");
        }
    }

    private Comment getCommentById(long id) {
        return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
    }

    private void validateCommentPostId(long commentPostId, long postId) {
        if (commentPostId != postId) {
            throw new IllegalArgumentException("게시글에 해당 댓글이 존재하지 않습니다.");
        }
    }

    private void validateCommentWriterId(long commentWriterId, long writerId) {
        if (commentWriterId != writerId) {
            throw new NoAuthorizationException();
        }
    }
}
