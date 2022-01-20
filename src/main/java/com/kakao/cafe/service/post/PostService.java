package com.kakao.cafe.service.post;

import com.kakao.cafe.domain.comment.CommentRepository;
import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.exception.NoAuthorizationException;
import com.kakao.cafe.model.post.PostDto;
import com.kakao.cafe.model.post.PostWriteRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public void writePost(PostWriteRequest post, long writerId) {
        try {
            postRepository.save(Post.builder()
                    .writerId(writerId)
                    .title(post.getTitle())
                    .content(post.getContent())
                    .createdAt(LocalDateTime.now())
                    .build());
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("게시글 등록에 실패하였습니다.");
        }
    }

    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 정보를 찾을 수 없습니다."));
        if (post.getDeleted()) {
            throw new IllegalArgumentException("삭제된 게시글 입니다.");
        }
        return modelMapper.map(post, PostDto.class);
    }

    public PostDto getPostById(long id, long writerId) {
        PostDto post = getPostById(id);
        if (!post.getWriterId().equals(writerId)) {
            throw new NoAuthorizationException();
        }
        return post;
    }

    public void updatePost(long id, long writerId, PostWriteRequest request) {
        PostDto post = getPostById(id, writerId);
        try {
            postRepository.update(Post.builder()
                    .id(post.getId())
                    .title(request.getTitle())
                    .content(request.getContent())
                    .updatedAt(LocalDateTime.now())
                    .build());
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("게시글 수정에 실패하였습니다.");
        }
    }

    @Transactional
    public void deletePost(long id, long writerId) {
        PostDto post = getPostById(id, writerId);
        checkCanDeletePost(post.getId(), writerId);

        try {
            postRepository.updateDeletedById(id);
            commentRepository.updateDeletedByPostId(post.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("게시글 삭제에 실패하였습니다.");
        }
    }

    private void checkCanDeletePost(long id, long writerId) {
        long countOfComments = commentRepository.countByPostIdAndWriterIdNot(id, writerId);
        if (countOfComments > 0) {
            throw new IllegalArgumentException("다른 작성자가 작성한 댓글이 남아있기 때문에 게시글을 삭제할 수 없습니다.");
        }
    }
}
