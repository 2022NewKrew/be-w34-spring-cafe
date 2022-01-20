package com.kakao.cafe.service;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.dto.comment.CommentViewDto;
import com.kakao.cafe.dto.comment.SaveCommentDto;
import com.kakao.cafe.error.exception.nonexist.CommentNotFoundedException;
import com.kakao.cafe.error.msg.CommentErrorMsg;
import com.kakao.cafe.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Comment findByUserIdAndPostId(Long userId, Long postId) {
        Optional<Comment> optionalComment = commentRepository.findCommentByUserIdAndPostId(userId, postId);
        return optionalComment.orElseThrow(() -> new CommentNotFoundedException(CommentErrorMsg.COMMENT_NOT_FOUNDED.getDescription()));
    }

    @Override
    public List<CommentViewDto> getCommentViewDtoListByPostId(Long postId) {
        return commentRepository.getCommentViewDtoListByPostId(postId);
    }

    @Override
    public Comment addComment(SaveCommentDto saveCommentDto, Long userId) {
        Comment newComment = createCommentBy(saveCommentDto, userId);
        return commentRepository.save(newComment);
    }

    private Comment createCommentBy(SaveCommentDto saveCommentDto, Long userId) {
        return Comment.builder()
                .contents(saveCommentDto.getContents())
                .postId(saveCommentDto.getPostId())
                .userId(userId)
                .createdAt(OffsetDateTime.now())
                .build();
    }

    @Override
    public Comment updateComment(SaveCommentDto saveCommentDto, Long userId) {
        Long postId = saveCommentDto.getPostId();
        Comment targetComment = findByUserIdAndPostId(userId, postId);
        updateCommentBySaveCommentDto(targetComment, saveCommentDto);
        return commentRepository.save(targetComment);
    }

    private void updateCommentBySaveCommentDto(Comment comment, SaveCommentDto saveCommentDto) {
        comment.setContents(saveCommentDto.getContents());
    }

    @Override
    public void deleteByCommentIdAndUserId(Long commentId, Long userId) {
        int effectedRowNum = commentRepository.deleteByCommentIdAndUserId(commentId, userId);
        if (effectedRowNum == 0) {
            throw new CommentNotFoundedException(CommentErrorMsg.COMMENT_NOT_FOUNDED.getDescription());
        }
    }
}
