package com.kakao.cafe.repository;

import com.kakao.cafe.DB.RogerDB;
import com.kakao.cafe.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private final RogerDB rogerDB;

    @Autowired
    public PostRepositoryImpl(RogerDB rogerDB) {
        this.rogerDB = rogerDB;
    }

    @Override
    public int save(Post post) {
        rogerDB.getPost().add(post);
        return 0;
    }

    @Override
    public Optional<List<Post>> findAll() {
        return Optional.ofNullable(rogerDB.getPost());
    }

    @Override
    public Optional<Post> findById(int questionId) {
        return rogerDB.getPost().stream()
                .filter(post -> post.getId() == questionId)
                .findFirst();
    }

    @Override
    public void update(Post post) {

    }
}
