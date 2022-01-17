package com.kakao.cafe.service;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.dto.post.AddPostDto;
import com.kakao.cafe.dto.post.PostViewDto;
import com.kakao.cafe.dto.post.SimplePostInfo;
import com.kakao.cafe.error.exception.nonexist.PostNotFoundedException;
import com.kakao.cafe.error.exception.nonexist.UserNotFoundedException;
import com.kakao.cafe.error.msg.PostErrorMsg;
import com.kakao.cafe.error.msg.UserErrorMsg;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Override
    public PostViewDto findPostViewDtoById(Long postId) {
        Optional<PostViewDto> optionalPostViewDto = postRepository.findPostViewDtoById(postId);
        return optionalPostViewDto.orElseThrow(() -> new PostNotFoundedException(PostErrorMsg.POST_NOT_FOUNDED.getDescription()));
    }

    @Override
    public List<SimplePostInfo> getListOfSimplePostInfo(Integer pageNum, Integer pageSize) {
        return postRepository.getListOfSimplePostInfo(pageNum, pageSize);
    }

    @Override
    public Post addPost(AddPostDto addPostDto, Long writerId) {
        if(!userRepository.existsById(writerId)) {
            throw new UserNotFoundedException(UserErrorMsg.USER_NOT_FOUNDED.getDescription());
        }
        Post newPost = createPostBy(addPostDto, writerId);
        postRepository.save(newPost);
        return newPost;
    }

    private Post createPostBy(AddPostDto addPostDto, Long writerId) {
        return Post.builder()
                .title(addPostDto.getTitle())
                .contents(addPostDto.getContents())
                .userId(writerId)
                .build();
    }

    @Override
    public int countAll() {
        return postRepository.countAll();
    }
}
