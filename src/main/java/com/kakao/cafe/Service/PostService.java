package com.kakao.cafe.Service;

import com.kakao.cafe.Dto.Login.LoginAuthDto;
import com.kakao.cafe.Dto.Post.DetailedPostResponseDto;
import com.kakao.cafe.Dto.Reply.ReplyResponseDto;
import com.kakao.cafe.Exception.NotAuthorizedException;
import com.kakao.cafe.Exception.NotLoginException;
import com.kakao.cafe.Repository.PostDao;
import com.kakao.cafe.Dto.Post.PostRequestDto;
import com.kakao.cafe.Dto.Post.PostResponseDto;
import com.kakao.cafe.Repository.ReplyDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostDao postDao;
    private final ReplyDao replyDao;

    public void createQuestion(PostRequestDto post) {
        postDao.insert(post);
    }

    public List<PostResponseDto> getPostList() {
        return postDao.findAll();
    }

    public PostResponseDto findPostById(int id) {
        return postDao.findById(id);
    }

    public DetailedPostResponseDto findDetailedPostById(int id) {
        PostResponseDto post = postDao.findById(id);
        List<ReplyResponseDto> replyList = replyDao.findByPostId(id);
        return new DetailedPostResponseDto(post, replyList);
    }

    public void editQuestion(int id, PostRequestDto post) {
        postDao.update(id, post);
    }

    public void chkLogin(LoginAuthDto authInfo) {
        if (authInfo == null) {
            throw new NotLoginException("로그인이 필요합니다.");
        }
    }

    public void chkAuth(LoginAuthDto authInfo, PostResponseDto post) {
        if (!Objects.equals(post.getWriter(), authInfo.getUserId())) {
            throw new NotAuthorizedException("게시글 수정 권한이 없습니다.");
        }
    }

    public void deleteById(int id, LoginAuthDto authDto) {
        if (!postDao.findById(id).getWriter().equals(authDto.getUserId())) {
            throw new NotAuthorizedException("게시글 삭제 권한이 없습니다.");
        }
        postDao.deleteById(id);
    }
}
