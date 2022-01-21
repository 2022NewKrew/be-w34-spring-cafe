package com.kakao.cafe.service;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.domain.reply.ReplyRepository;
import com.kakao.cafe.dto.post.CreatePostDto;
import com.kakao.cafe.dto.post.ShowPostDto;
import com.kakao.cafe.dto.post.UpdatePostDto;
import com.kakao.cafe.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    public ShowPostDto createPost(CreatePostDto postDto) {
        Post savePost = postRepository.save(Post.builder()
                .writer(postDto.getWriter())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build());

        return new ShowPostDto(savePost);
    }

    public ShowPostDto findPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 게시글 입니다."));

        return new ShowPostDto(post);
    }

    public List<ShowPostDto> findAllPost() {
        return postRepository.findAll().stream()
                .map(ShowPostDto::new)
                .collect(Collectors.toList());
    }


    public ShowPostDto updatePost(Long id, UpdatePostDto postDto) {
        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .writer(postDto.getWriter())
                .build();

        post.setId(id);
        return new ShowPostDto(postRepository.edit(id, post));
    }

    public void deletePost(Long id, String userId) {
        replyRepository.findAll(id).forEach(reply -> {
            if(!reply.getUserId().equals(userId)){
                throw new IllegalArgumentException("다른 사용자의 댓글이 있으면 삭제할 수 없습니다.");
            }
        });

        postRepository.remove(id);
    }

}
