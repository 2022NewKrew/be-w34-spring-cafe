package com.kakao.cafe.service;

import com.kakao.cafe.domain.comment.Comment;
import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.util.exception.throwable.UnauthorizedActionException;
import com.kakao.cafe.util.exception.wrappable.CommentNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CommentServiceTest {

    private final CommentService commentService;
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public CommentServiceTest(CommentService commentService, PostService postService, UserService userService) {
        this.commentService = commentService;
        this.postService = postService;
        this.userService = userService;
    }

    @Test
    @Transactional
    void insertSuccess() {
        Post post = postService.findById(1);
        User user = userService.findById("javajigi");
        Comment comment = new Comment.Builder()
                .post(post)
                .user(user)
                .text("hellow Worldy")
                .build();
        assertEquals(commentService.insert(comment), 1);
    }

    @Test
    @Transactional
    void findAll() {
        assertEquals(commentService.findAll(1).size(), 1);
    }

    @Test
    @Transactional
    void findById() {
        Comment comment = commentService.findById(1);
        assertEquals(comment.getId(), 1);
    }

    @Test
    @Transactional
    void findInvalidIdFail() {
        assertThatThrownBy(() -> commentService.findById(-1))
                .isInstanceOf(CommentNotFoundException.class);
    }


    @Test
    @Transactional
    void deleteFailByWrongUser() {
        Comment comment = commentService.findById(1);
        assertThatThrownBy(() -> commentService.delete(comment, "anyone"))
                .isInstanceOf(UnauthorizedActionException.class);
    }

    @Test
    @Transactional
    void updateFailByWrongUser() {
        Comment comment = commentService.findById(1);
        assertThatThrownBy(() -> commentService.update(comment, "wrongGuy"))
                .isInstanceOf(UnauthorizedActionException.class);
    }


}
