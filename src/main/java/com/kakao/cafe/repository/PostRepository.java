package com.kakao.cafe.repository;


import com.kakao.cafe.domain.Post;

import java.util.Optional;

public class PostRepository implements Repository<Post, Long>{
    @Override
    public Optional<Post> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public void delete(Post post) {

    }

    @Override
    public int countAll() {
        return 0;
    }
}
