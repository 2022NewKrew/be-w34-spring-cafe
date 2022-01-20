package com.kakao.cafe.Service;

import com.kakao.cafe.Dto.Login.LoginAuthDto;
import com.kakao.cafe.Exception.NotAuthorizedException;
import com.kakao.cafe.Repository.PostDao;
import com.kakao.cafe.Dto.Post.PostRequestDto;
import com.kakao.cafe.Dto.Post.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostDao postDao;

    public void createQuestion(PostRequestDto post) {
        postDao.insert(post);
    }

    public List<PostResponseDto> getPostList() {
        return postDao.findAll();
    }

    public PostResponseDto findPostById(int id) {
        return postDao.findById(id);
    }

    public void editQuestion(int id, PostRequestDto post) {
        postDao.update(id, post);
    }

    public void deleteById(int id, LoginAuthDto authDto) {
        if (!postDao.findById(id).getWriter().equals(authDto.getUserId())) {
            throw new NotAuthorizedException("게시글 삭제 권한이 없습니다.");
        }
        postDao.deleteById(id);
    }
}
