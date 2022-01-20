package com.kakao.cafe.qna.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by melodist
 * Date: 2022-01-19 019
 * Time: 오후 10:46
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment insertComment(Integer id, Integer articleIdx, String userId, String contents) {
        Comment comment = new Comment(id, articleIdx, userId, contents);
        return commentRepository.insert(comment);
    }
}
