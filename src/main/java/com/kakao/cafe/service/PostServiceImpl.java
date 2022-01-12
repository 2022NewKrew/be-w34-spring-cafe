package com.kakao.cafe.service;

import com.kakao.cafe.dto.PageRequestDto;
import com.kakao.cafe.dto.PageResultDto;
import com.kakao.cafe.dto.PostDto;
import com.kakao.cafe.entity.Post;
import com.kakao.cafe.exception.CafeException;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Long register(PostDto dto) {
        Post entity = dtoToEntity(dto);
        return postRepository.save(entity);
    }

    @Override
    public PostDto getPost(Long postId) {
        Optional<Post> result = postRepository.findById(postId);
        if (result.isEmpty())
            throw new CafeException();
        return entityToDto(result.get());
    }

    @Override
    public PageResultDto<PostDto, Post> getList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable();
        Page<Post> result = postRepository.findAll(pageable);
        Function<Post, PostDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<>(result, fn);
    }
}
