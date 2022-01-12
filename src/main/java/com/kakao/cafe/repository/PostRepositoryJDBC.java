package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Post;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("postRepositoryJDBC")
public class PostRepositoryJDBC implements PostRepository {
    @Override
    public void save(Post post) {

    }

    @Override
    public Optional<List<Post>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<Post> findById(int questionId) {
        return Optional.empty();
    }
}
