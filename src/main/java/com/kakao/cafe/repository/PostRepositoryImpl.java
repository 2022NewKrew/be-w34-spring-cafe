package com.kakao.cafe.repository;

import com.kakao.cafe.DB.RogerDB;
import com.kakao.cafe.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private final RogerDB rogerDB;

    @Autowired
    public PostRepositoryImpl(RogerDB rogerDB) {
        this.rogerDB = rogerDB;
    }

    @Override
    public void save(Post post) {
        rogerDB.getPost().add(post);
    }
}
