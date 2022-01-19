package com.kakao.cafe.service.post;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.exception.NoAuthorizationException;
import com.kakao.cafe.model.post.PostDto;
import com.kakao.cafe.model.post.PostWriteRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostService(@Qualifier("postJdbcRepositoryImpl") PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    public void writePost(PostWriteRequest post, long writerId) {
        try {
            postRepository.save(Post.builder()
                    .writerId(writerId)
                    .title(post.getTitle())
                    .content(post.getContent())
                    .createdAt(LocalDateTime.now())
                    .build());
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("게시글 등록에 실패하였습니다.");
        }
    }

    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 정보를 찾을 수 없습니다."));;
        return modelMapper.map(post, PostDto.class);
    }

    public PostDto getPostById(long id, long writerId) {
        PostDto post = getPostById(id);
        if (!post.getWriterId().equals(writerId)) {
            throw new NoAuthorizationException();
        }
        return post;
    }

    public void updatePost(long id, long writerId, PostWriteRequest request) {
        PostDto post = getPostById(id, writerId);
        try {
            postRepository.update(Post.builder()
                    .id(post.getId())
                    .title(request.getTitle())
                    .content(request.getContent())
                    .updatedAt(LocalDateTime.now())
                    .build());
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("게시글 수정에 실패하였습니다.");
        }
    }

    public void deletePost(long id, long writerId) {
        PostDto post = getPostById(id, writerId);
        try {
            postRepository.deleteById(post.getId());
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("게시글 삭제에 실패하였습니다.");
        }
    }

}
