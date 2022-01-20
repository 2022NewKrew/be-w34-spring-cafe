package com.kakao.cafe.service.comment;

import com.kakao.cafe.domain.comment.Comment;
import com.kakao.cafe.domain.comment.CommentRepository;
import com.kakao.cafe.exception.NoAuthorizationException;
import com.kakao.cafe.model.comment.CommentWriteRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;
    private Comment comment;

    @BeforeEach
    void setup() {
        long postId = 1;
        long writerId = 1;
        String content = "댓글 내용입니다.";
        Comment comment = Comment.builder()
                .postId(postId)
                .writerId(writerId)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();
        commentRepository.save(comment);
        this.comment = commentRepository.findByPostId(postId).stream().max((c1, c2) -> (int) (c1.getId() - c2.getId())).orElse(null);
    }

    @DisplayName("댓글 등록하기 - 댓글 목록의 크기가 1 증가해야 한다.")
    @Test
    void writeComment() {
        long postId = 1;
        long writerId = 1;
        String content = "댓글 내용입니다.";
        CommentWriteRequest request = CommentWriteRequest.builder()
                .content(content)
                .build();
        List<Comment> commentsBeforeSave = commentRepository.findByPostId(postId);

        assertThatNoException().isThrownBy(() -> {
            commentService.saveComment(postId, writerId, request);

            List<Comment> commentsAfterSave = commentRepository.findByPostId(postId);
            assertThat(commentsAfterSave.size()).isEqualTo(commentsBeforeSave.size() + 1);
        });
    }

    @DisplayName("댓글 삭제하기 - 댓글을 삭제하면 댓글 목록의 크기가 1 감소해야 한다.")
    @Test
    void deleteById() {
        long id = comment.getId();
        long postId = comment.getPostId();
        long writerId = comment.getWriterId();
        List<Comment> commentsBeforeDelete = commentRepository.findByPostId(postId);

        assertThatNoException().isThrownBy(() -> {
            commentService.deleteById(id, postId, writerId);

            List<Comment> commentsAfterDelete = commentRepository.findByPostId(postId);
            assertThat(commentsAfterDelete.size()).isEqualTo(commentsBeforeDelete.size() - 1);
        });
    }

    @DisplayName("댓글 삭제하기 - 댓글이 존재하지 않으면 에러를 발생해야 한다.")
    @Test
    void deleteById_idNotExist() {
        long id = comment.getId() + 1;
        long postId = comment.getPostId();
        long writerId = comment.getWriterId();

        assertThatIllegalArgumentException().isThrownBy(() -> {
            commentService.deleteById(id, postId, writerId);
        });
    }

    @DisplayName("댓글 삭제하기 - 댓글의 게시글 id와 일치하지 않으면 에러를 발생해야 한다.")
    @Test
    void deleteById_postIdNotMatch() {
        long id = comment.getId() ;
        long postId = comment.getPostId() + 1;
        long writerId = comment.getWriterId();

        assertThatIllegalArgumentException().isThrownBy(() -> {
            commentService.deleteById(id, postId, writerId);
        });
    }

    @DisplayName("댓글 삭제하기 - 댓글의 작성자 id와 일치하지 않으면 에러를 발생해야 한다.")
    @Test
    void deleteById_writerIdNotMatch() {
        long id = comment.getId() ;
        long postId = comment.getPostId();
        long writerId = comment.getWriterId() + 1;

        assertThatExceptionOfType(NoAuthorizationException.class).isThrownBy(() -> {
            commentService.deleteById(id, postId, writerId);
        });
    }

}
