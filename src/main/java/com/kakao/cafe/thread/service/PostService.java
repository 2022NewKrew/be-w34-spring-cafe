package com.kakao.cafe.thread.service;

import com.kakao.cafe.exception.PostNotFoundException;
import com.kakao.cafe.exception.UnauthorizedAccessException;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.thread.domain.Post;
import com.kakao.cafe.thread.domain.ThreadStatus;
import com.kakao.cafe.thread.dto.PostCreationForm;
import com.kakao.cafe.thread.dto.PostView;
import com.kakao.cafe.thread.repository.PostRepository;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserView;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private PostView toPostView(Post post) {
        User user = userRepository.getById(post.getAuthorId()).orElseThrow(UserNotFoundException::new);
        return new PostView(post.getId(), new UserView(user.getUsername(), user.getEmail(), user.getDisplayName()),
                            post.getTitle(), post.getContent(),
                            post.getCreatedAt(), post.getLastModifiedAt());
    }

    public Long addFromForm(Long authorId, PostCreationForm postCreationForm) {
        return postRepository.add(Post.builder()
                                      .authorId(authorId)
                                      .title(postCreationForm.getTitle())
                                      .content(postCreationForm.getContent())
                                      .status(ThreadStatus.VALID.name())
                                      .build());
    }

    public List<PostView> getAll() {
        return postRepository.getAll().stream().map(this::toPostView).collect(Collectors.toList());
    }

    public PostView get(Long id) {
        return toPostView(postRepository.get(id).orElseThrow(PostNotFoundException::new));
    }

    public void updateFromForm(Long authorId, Long postId, PostCreationForm postCreationForm) {
        Post post = postRepository.get(postId).orElseThrow(PostNotFoundException::new);
        validateUserPermissionOnPost(authorId, post);
        postRepository.update(Post.builder()
                                  .id(postId)
                                  .title(postCreationForm.getTitle())
                                  .content(postCreationForm.getContent())
                                  .status(post.getStatus())
                                  .build());
    }

    public void softDelete(Long authorId, Long postId) {
        Post post = postRepository.get(postId).orElseThrow(PostNotFoundException::new);
        validateUserPermissionOnPost(authorId, post);
        postRepository.update(Post.builder()
                                  .id(postId)
                                  .title(post.getTitle())
                                  .content(post.getContent())
                                  .status(ThreadStatus.DELETED.name())
                                  .build());
    }

    private void validateUserPermissionOnPost(Long authorId, Post post) {
        // The permission check is done here not part of Post domain
        // More complicated permission would require more knowledge than Post has access to
        if (!post.getAuthorId().equals(authorId)) {
            throw new UnauthorizedAccessException();
        }
    }
}
