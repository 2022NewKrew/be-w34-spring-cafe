package com.kakao.cafe.service;

import com.kakao.cafe.domain.posts.PostsRepository;
import com.kakao.cafe.web.dto.PostResponseDto;
import com.kakao.cafe.web.dto.PostsCreateRequestDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public class PostsService {

    private final PostsRepository postsRepository = new PostsRepository();

    @Transactional
    public void save(PostsCreateRequestDto requestDto) {
        postsRepository.save(requestDto.toEntity());
    }

    @Transactional(readOnly = true)
    public ArrayList<PostResponseDto> findAll() {
        return postsRepository.findAll();
    }

}
