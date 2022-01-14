package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Post;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class PostRepository {
    private List<Post> postList;
    private Long postId;

    public PostRepository() {
        postList = new ArrayList<>();
        postId = 1L;
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
