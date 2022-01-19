package com.kakao.cafe.service;

import com.kakao.cafe.dto.PostCreateRequest;
import com.kakao.cafe.dto.PostDetailDto;
import com.kakao.cafe.dto.PostUpdateRequest;
import com.kakao.cafe.exception.CustomException;
import com.kakao.cafe.exception.ErrorCode;
import com.kakao.cafe.exception.ForbiddenException;
import com.kakao.cafe.model.Post;
import com.kakao.cafe.model.User;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository jdbcPostRepository, UserRepository jdbcUserRepository) {
        this.postRepository = jdbcPostRepository;
        this.userRepository = jdbcUserRepository;
    }

    public List<PostDetailDto> getAllPostDetail() {
        List<Post> postList = postRepository.findAll();
        List<PostDetailDto> postDetailDtoList = new ArrayList<>();

        for (Post post : postList) {
            User writer = userRepository.findById(post.getWriterId())
                    .orElseThrow(() -> new CustomException(ErrorCode.NO_USER_MATCHED_INPUT));

            postDetailDtoList.add(new PostDetailDto(
                    post.getId(),
                    post.getTitle(),
                    writer.getId(),
                    writer.getName(),
                    post.getContent(),
                    post.getCreatedAt())
            );
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
                .orElseThrow(() -> new CustomException(ErrorCode.NO_USER_MATCHED_INPUT));

        return new PostDetailDto(
                post.getId(),
                post.getTitle(),
                writer.getId(),
                writer.getName(),
                post.getContent(),
                post.getCreatedAt());
    }

    public boolean isWriter(UUID postWriterId, UUID sessionUserId) {
        return sessionUserId.equals(postWriterId);
    }

    public void validateWriter(Post post, UUID sessionUserId) {
        if (!isWriter(post.getWriterId(), sessionUserId)) {
            throw new ForbiddenException("수정/삭제는 게시글 작성자만 가능합니다.");
        }
    }

    @Transactional
    public void update(UUID postId, PostUpdateRequest requestDto, UUID sessionUserId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        validateWriter(post, sessionUserId);
        postRepository.update(requestDto.toEntity(post.getWriterId(), postId));
    }
}
