package com.kakao.cafe.service;

import com.kakao.cafe.dto.PostCreateRequest;
import com.kakao.cafe.dto.PostDetailDto;
import com.kakao.cafe.dto.PostUpdateRequest;
import com.kakao.cafe.exception.CustomException;
import com.kakao.cafe.exception.ErrorCode;
import com.kakao.cafe.model.Post;
import com.kakao.cafe.model.User;
import com.kakao.cafe.repository.post.PostRepository;
import com.kakao.cafe.repository.user.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository jdbcPostRepository, UserRepository jdbcUserRepository) {
        this.postRepository = jdbcPostRepository;
        this.userRepository = jdbcUserRepository;
    }

    public List<PostDetailDto> getAllPostDetail() {
        List<Post> postList = postRepository.findAll();
        List<PostDetailDto> postDetailDtoList = new ArrayList<>();

        for (Post post : postList) {
            User writer = userRepository.findById(post.getWriterId())
                    .orElseThrow(() -> new CustomException(ErrorCode.NO_USER_MATCHED_WRITER));

            postDetailDtoList.add(PostDetailDto.of(post, writer));
        }

        return postDetailDtoList;
    }

    public void write(PostCreateRequest requestDto, User writer) {
        postRepository.save(requestDto.toEntity(writer.getId()));
    }

    public PostDetailDto getPostDetailById(UUID id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        User writer = userRepository.findById(post.getWriterId())
                .orElseThrow(() -> new CustomException(ErrorCode.NO_USER_MATCHED_WRITER));

        return PostDetailDto.of(post, writer);
    }

    public boolean isWriter(UUID postWriterId, UUID sessionUserId) {
        return sessionUserId.equals(postWriterId);
    }

    public void validateWriter(Post post, UUID sessionUserId) {
        if (!isWriter(post.getWriterId(), sessionUserId)) {
            throw new CustomException(ErrorCode.FORBIDDEN_MODIFY_POST);
        }
    }

    @Transactional
    public void update(UUID postId, PostUpdateRequest requestDto, UUID sessionUserId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        validateWriter(post, sessionUserId);
        postRepository.update(requestDto.toEntity(post.getWriterId(), postId));
    }

    @Transactional
    public void delete(UUID postId, UUID sessionUserId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        validateWriter(post, sessionUserId);
        postRepository.delete(post);
    }
}
