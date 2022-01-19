package com.kakao.cafe.thread.service;

import com.kakao.cafe.exception.PostNotFoundException;
import com.kakao.cafe.exception.UserNotFoundException;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    private Post toPost(PostCreationForm postCreationForm) {
        Optional<User> user = userRepository.get(postCreationForm.getAuthor_username());
        return new Post(null, 1L, user.orElseThrow(UserNotFoundException::new).getId(), postCreationForm.getTitle(), postCreationForm.getContent(),
                        ThreadStatus.VALID.name(), null, null);
    }

    private PostView toPostView(Post post) {
        User user = userRepository.get(post.getAuthorId()).get();
        return new PostView(post.getId(), new UserView(user.getUsername(), user.getEmail(), user.getDisplayName()), post.getTitle(), post.getContent(),
                            post.getCreatedAt(), post.getLastModifiedAt());
    }

    public Long addFromForm(PostCreationForm postCreationForm) {
        return postRepository.add(toPost(postCreationForm));
    }

    public List<PostView> getAll() {
        return postRepository.getAll().stream().map(post -> toPostView(post)).collect(Collectors.toList());
    }

    public PostView get(Long id) {
        return toPostView(postRepository.get(id).orElseThrow(PostNotFoundException::new));
    }
}
