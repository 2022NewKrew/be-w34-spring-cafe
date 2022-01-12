package com.kakao.cafe.domain.post.impl;

import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.interfaces.common.PostDto;
import com.kakao.cafe.interfaces.common.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryPostRepository implements PostRepository {
    private static final Map<Long, PostDto> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    @Override
    public PostDto add(PostDto postDto) {
        postDto.setId(++sequence);
        store.put(postDto.getId(), postDto);
        return postDto;
    }

    @Override
    public Optional<PostDto> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<PostDto> findAllByWriter(UserDto user) {
        return store.values().stream()
                .filter(postDto -> postDto.getWriter().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findAll() {
        return new ArrayList<>(store.values());
    }
}
