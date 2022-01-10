package com.kakao.cafe.service;

import com.kakao.cafe.dto.PageRequestDto;
import com.kakao.cafe.dto.PageResultDto;
import com.kakao.cafe.dto.PostDto;
import com.kakao.cafe.entity.Post;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Long register(PostDto dto) {
        Post entity = dtoToEntity(dto);
        log.info(entity);
        postRepository.save(entity);
        return entity.getPostId();
    }

    @Override
    public PageResultDto<PostDto, Post> getList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable();
        Page<Post> result = postRepository.findAll(pageable);
        Function<Post, PostDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<>(result, fn);
    }
}
