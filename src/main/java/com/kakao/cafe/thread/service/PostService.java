package com.kakao.cafe.thread.service;

import com.kakao.cafe.exception.PostNotFoundException;
import com.kakao.cafe.thread.domain.Post;
import com.kakao.cafe.thread.domain.ThreadStatus;
import com.kakao.cafe.thread.dto.PostCreationForm;
import com.kakao.cafe.thread.dto.PostView;
import com.kakao.cafe.thread.repository.PostRepository;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserView;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    private PostView toPostView(Post post) {
        User user = userRepository.getById(post.getAuthorId()).get();
        return new PostView(post.getId(), new UserView(user.getUsername(), user.getEmail(), user.getDisplayName()),
                            post.getTitle(), post.getContent(),
                            post.getCreatedAt(), post.getLastModifiedAt());
    }

    public Long addFromForm(Long authorId, PostCreationForm postCreationForm) {
        return postRepository.add(Post.builder()
                                      .parentId(1L)
                                      .authorId(authorId)
                                      .title(postCreationForm.getTitle())
                                      .content(postCreationForm.getContent())
                                      .status(ThreadStatus.VALID.name())
                                      .build());
    }

    public List<PostView> getAll() {
        return postRepository.getAll().stream().map(post -> toPostView(post)).collect(Collectors.toList());
    }

    public PostView get(Long id) {
        return toPostView(postRepository.get(id).orElseThrow(PostNotFoundException::new));
    }
}
