package com.kakao.cafe.thread;

import com.kakao.cafe.user.User;
import com.kakao.cafe.user.UserMapper;
import com.kakao.cafe.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private Post toPost(PostCreationForm postCreationForm) {
        User user = userRepository.get(postCreationForm.getAuthor_username()).get();
        return new Post(null, 1L, user.getId(), postCreationForm.getTitle(), postCreationForm.getContent(), ThreadStatus.VALID.name(), null, null);
    }

    private PostView toPostView(Post post) {
        User user = userRepository.get(post.getAuthor_id()).get();
        return new PostView(post.getId(), UserMapper.toUserViewDTO(user), post.getTitle(), post.getContent(), post.getCreated_at(), post.getLast_modified_at());
    }

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Long addFromForm(PostCreationForm postCreationForm) {
        return postRepository.add(toPost(postCreationForm));
    }

    public List<PostView> getAll() {
        return postRepository.getAll().stream().map(post -> toPostView(post)).collect(Collectors.toList());
    }

    public PostView get(Long id) {
        return toPostView(postRepository.get(id).get());
    }
}
