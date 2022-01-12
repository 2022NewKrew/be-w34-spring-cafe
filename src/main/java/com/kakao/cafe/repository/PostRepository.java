package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Post;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Repository
public class PostRepository {
    private List<Post> postList;
    private Long postId;

    public PostRepository() {
        postList = new ArrayList<>();
        postId = 1L;

        save(Post.builder()
                .postId(1L)
                .title("첫 번째 글입니다.")
                .content("안녕하세요.\n카카오 교육 페이지의 첫 번째 글입니다.")
                .writerEmail("kina.lee@kakaocorp.com")
                .viewCount(10L)
                .build());
        save(Post.builder()
                .postId(2L)
                .title("두 번째 글입니다.")
                .content("안녕하세요.\n카카오 교육 페이지의 두 번째 글입니다.")
                .writerEmail("kina.lee@kakaocorp.com")
                .viewCount(10L)
                .build());
        save(Post.builder()
                .postId(3L)
                .title("세 번째 글입니다.")
                .content("안녕하세요.\n카카오 교육 페이지의 세 번째 글입니다.")
                .writerEmail("kina.lee@kakaocorp.com")
                .viewCount(10L)
                .build());
        IntStream.rangeClosed(1, 100).forEach(value -> {
            save(Post.builder()
                    .postId(1L)
                    .title(String.format("Dummy Post %d", value))
                    .content(String.format("Dummy Content %d\n", value))
                    .writerEmail(String.format("tester.%d@kakaocorp.com", value))
                    .viewCount(10L)
                    .build());
        });
    }

    public Long save(Post entity) {
        postList.add(0, entity.init(postId));
        return postId++;
    }

    public Optional<Post> findById(Long postId) {
        return postList.stream()
                .filter(post -> post.getPostId().equals(postId))
                .findFirst();
    }

    public Page<Post> findAll(Pageable pageable) {
        int totalPage = (int) Math.ceil(postList.size() / (double) pageable.getSize());
        int fromIndex = pageable.getPage() * pageable.getSize();
        if (fromIndex >= postList.size())
            return new Page<Post>(new ArrayList<>(), totalPage, postList.size(), pageable);
        int toIndex = fromIndex + pageable.getSize();
        if (toIndex >= postList.size())
            toIndex = postList.size();
        return new Page<Post>(postList.subList(fromIndex, toIndex), totalPage, postList.size(), pageable);
    }
}
