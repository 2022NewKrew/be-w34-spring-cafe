package com.kakao.cafe.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    void deleteByIdAndUserId() {
        System.out.println(postRepository.countAll());
        int row = postRepository.deleteByIdAndUserId(Long.valueOf(1), Long.valueOf(1));
        System.out.println(row);
        System.out.println(postRepository.countAll());
    }
}