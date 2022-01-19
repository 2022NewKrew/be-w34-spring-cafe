package com.kakao.cafe.Service;

import com.kakao.cafe.Repository.PostDao;
import com.kakao.cafe.model.Post.PostCreateRequestDto;
import com.kakao.cafe.model.Post.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostDao postDao;

    public void createQuestion(PostCreateRequestDto post) {
        postDao.insert(post);
    }

    public List<PostResponseDto> getPostList() {
        return postDao.findAll();
    }

    public PostResponseDto findPostById(Long id) {
        return postDao.findById(id);
    }
}
