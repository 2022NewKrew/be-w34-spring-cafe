package com.kakao.cafe.service;

import com.kakao.cafe.dto.PostCreateRequest;
import com.kakao.cafe.dto.PostDetailDto;
import com.kakao.cafe.exception.CustomException;
import com.kakao.cafe.exception.ErrorCode;
import com.kakao.cafe.model.Post;
import com.kakao.cafe.model.User;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void write(PostCreateRequest requestDto) {
        User writer = userRepository.findByUserId(requestDto.getWriterUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.NO_USER_MATCHED_INPUT));
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
}
