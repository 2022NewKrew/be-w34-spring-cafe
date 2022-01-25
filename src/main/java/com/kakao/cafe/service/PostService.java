package com.kakao.cafe.service;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.exceptions.UnauthenticatedPostAccessException;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.response.PostListResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ReplyService replyService;

    public PostService(PostRepository postRepository, UserRepository userRepository, ReplyService replyService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.replyService = replyService;
    }

    public void writePost(Post post) {
        postRepository.save(post);
    }

    public List<PostListResponse> getPostList() {
        return postRepository.findAll()
                .stream()
                .map(post -> PostListResponse.of(post, userRepository.findById(post.getUserId())))
                .collect(Collectors.toList());
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
        replyService.deleteReplyByPostId(postId);
        postRepository.delete(postId);
    }
}
