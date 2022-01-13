package com.kakao.cafe.post.domain.repository;

import com.kakao.cafe.post.domain.entity.Comment;
import com.kakao.cafe.post.domain.entity.Post;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface PostRepository {
    Optional<Post> getPost(Long id);
    List<Post> getPosts(int start, int size);
    void savePost(Post post);
    void saveComment(Long postId, Comment comment);
}
