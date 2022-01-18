package com.kakao.cafe.service;

import com.kakao.cafe.constant.OffsetId;
import com.kakao.cafe.domain.Post;
import com.kakao.cafe.dto.post.AddPostDto;
import com.kakao.cafe.dto.post.PostViewDto;
import com.kakao.cafe.dto.post.SimplePostInfo;
import com.kakao.cafe.dto.post.UpdatePostDto;
import com.kakao.cafe.error.exception.nonexist.PostNotFoundedException;
import com.kakao.cafe.error.exception.nonexist.UserNotFoundedException;
import com.kakao.cafe.error.exception.notmine.PostNotMineException;
import com.kakao.cafe.error.msg.PostErrorMsg;
import com.kakao.cafe.error.msg.UserErrorMsg;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Override
    public Post findById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElseThrow(() -> new PostNotFoundedException(PostErrorMsg.POST_NOT_FOUNDED.getDescription()));
    }

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
        if (!userRepository.existsById(writerId)) {
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
                .createdAt(OffsetDateTime.now(ZoneOffset.of(OffsetId.KR_ID)))
                .build();
    }

    @Override
    public Post updatePost(UpdatePostDto updatePostDto, Long writerId) {
        Long postId = updatePostDto.getPostId();
        Post targetPost = findById(postId);
        if(!writerId.equals(targetPost.getUserId())) {
            throw new PostNotMineException(PostErrorMsg.POST_NOT_MINE.getDescription());
        }
        updatePostByUpdatePostDto(targetPost, updatePostDto);
        return postRepository.save(targetPost);
    }

    private void updatePostByUpdatePostDto(Post post, UpdatePostDto updatePostDto) {
        post.setTitle(updatePostDto.getTitle());
        post.setContents(updatePostDto.getContents());
    }

    @Override
    public void increaseViewNumById(Long postId) {
        postRepository.increaseViewNumById(postId);
    }

    @Override
    public int countAll() {
        return postRepository.countAll();
    }

    @Override
    public void deleteByIdAndUserId(Long postId, Long userId) {
        int effectedRow = postRepository.deleteByIdAndUserId(postId, userId);
        if (effectedRow == 0) {
            throw new PostNotFoundedException(PostErrorMsg.POST_NOT_FOUNDED.getDescription());
        }
    }
}
