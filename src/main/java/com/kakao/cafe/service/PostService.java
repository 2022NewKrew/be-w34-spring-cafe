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

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<PostDetailDto> getAllPostDetail() {
        List<Post> postList = postRepository.findAll();
        List<PostDetailDto> postDetailDtoList = new ArrayList<>();

        for (Post post : postList) {
            User writer = userRepository.findById(post.getWriterId())
                    .orElseThrow(() -> new CustomException(ErrorCode.NO_MATCH_USER));

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

    public Post write(PostCreateRequest requestDto) {
        User writer = userRepository.findByName(requestDto.getWriter())
                .orElseThrow(() -> new CustomException(ErrorCode.NO_MATCH_USER));
        return postRepository.save(requestDto.toEntity(writer.getId()));
    }

    public PostDetailDto getPostDetailById(UUID id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
        User writer = userRepository.findById(post.getWriterId())
                .orElseThrow(() -> new CustomException(ErrorCode.NO_MATCH_USER));

        return new PostDetailDto(
                post.getId(),
                post.getTitle(),
                writer.getId(),
                writer.getName(),
                post.getContent(),
                post.getCreatedAt());
    }
}
