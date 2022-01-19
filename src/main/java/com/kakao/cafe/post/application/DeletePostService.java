package com.kakao.cafe.post.application;

import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePostService {
    private final PostRepository postRepository;

    public void softDelete(Long postId, String writerName){
        Post post = postRepository.getPost(postId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글이 없습니다.")
        );

        if(!writerName.equals(post.getWriterName())){
            throw new IllegalStateException("작성자만 글을 삭제할 수 있습니다.");
        }

        postRepository.softDelete(postId);
    }
}
