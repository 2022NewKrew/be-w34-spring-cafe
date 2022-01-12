package com.kakao.cafe.service;

import com.kakao.cafe.dto.post.PostViewDto;
import com.kakao.cafe.dto.post.SimplePostInfo;
import com.kakao.cafe.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public PostViewDto findPostViewDtoById(Long postId) {
        return null;
    }

    @Override
    public List<SimplePostInfo> getListOfSimplePostInfo(Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }
}
