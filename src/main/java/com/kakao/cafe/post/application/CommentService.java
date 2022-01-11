package com.kakao.cafe.post.application;

import com.kakao.cafe.post.domain.entity.Comment;
import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;

    public void addComment(Long id, Comment comment){
        Post post = postRepository.getPost(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id의 게시글이 없습니다."));

        post.addComment(comment);
    }
}
