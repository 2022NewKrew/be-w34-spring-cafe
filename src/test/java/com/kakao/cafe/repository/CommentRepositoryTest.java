package com.kakao.cafe.repository;

import com.kakao.cafe.domain.comment.Comment;
import com.kakao.cafe.domain.comment.Comments;
import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CommentRepositoryTest {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentRepositoryTest(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Test
    @Transactional
    void findByIdTest() {
        Comment comment = commentRepository.findById(1);
        assertThat(
                (comment.getId() == 1) &&
                        (comment.getPost().getId() == 1) &&
                        (comment.getUser().getId().equals("sanjigi"))
        ).isTrue();
    }

    @Test
    @Transactional
    void findAllTest() {
        Comments comments = commentRepository.findAll(1);
        assertThat(comments.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    void insertSuccess() {
        Post post = postRepository.findById(1);
        User user = userRepository.findById("javajigi");
        Comment comment = new Comment.Builder()
                .post(post)
                .user(user)
                .text("test")
                .build();


        assertThat(commentRepository.insert(comment)).isEqualTo(1);

    }

    @Test
    @Transactional
    void updateSccuess() {
        Comment comment = commentRepository.findById(1);
        Comment newComment = new Comment.Builder()
                .post(comment.getPost())
                .user(comment.getUser())
                .id(comment.getId())
                .text("new_text!")
                .build();
        assertEquals(commentRepository.update(newComment), 1);
        comment = commentRepository.findById(1);
        assertEquals(comment.getText(), newComment.getText());
    }

    @Test
    @Transactional
    void deleteSuccess() {
        commentRepository.delete(1);
        assertThatThrownBy(() -> commentRepository.findById(1))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    @Transactional
    void deleteAllSuccess() {
        int commentSize = commentRepository.findAll(1).size();
        assertThat(commentRepository.deleteAll(1)).isEqualTo(commentSize);
    }


}
