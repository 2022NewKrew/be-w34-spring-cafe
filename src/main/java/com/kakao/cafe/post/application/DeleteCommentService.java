package com.kakao.cafe.post.application;

import com.kakao.cafe.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCommentService {
    private final PostRepository postRepository;

    public void deleteComment(Long commentId) {
        postRepository.deleteComment(commentId);
    }
}
