package com.kakao.cafe.service;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.exceptions.UnauthenticatedPostAccessException;
import com.kakao.cafe.repository.PostRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void writePost(Post post) {
        postRepository.save(post);
    }

    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findByPostId(id);
    }

    private void validAuth(int postId, int userId, String errorMessage) {
        Post originalPost = getPostById(postId);
        if (originalPost.getUserId() != userId) {
            throw new UnauthenticatedPostAccessException(errorMessage);
        }
    }

    public Post getUpdatePostById(int postId, int userId) {
        validAuth(postId, userId, "본인 게시글만 수정할 수 있습니다");
        return postRepository.findByPostId(postId);
    }

    public void updatePost(Post updatePost) {
        validAuth(updatePost.getId(), updatePost.getUserId(), "본인 게시글만 수정할 수 있습니다");
        postRepository.update(updatePost);
    }

    public void deletePost(int postId, int userId) {
        validAuth(postId, userId, "본인 게시글만 삭제할 수 있습니다");
        postRepository.delete(postId);
    }
}
