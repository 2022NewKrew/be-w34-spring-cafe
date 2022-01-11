package com.kakao.cafe.repository;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.Posts;
import com.kakao.cafe.model.PostModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository.deleteAll();
    }

    @Test
    void insertAndFind() {
        PostModel postModel = new PostModel("writer1", "hello", "world");
        Post post = new Post(postModel);

        int id = postRepository.insert(post);
        Post newPost = postRepository.findById(id);
//        assertThat((post.getTitle().equals(newPost.getTitle()))
//                && (post.getTitle().equals(newPost.getTitle())
//                && (post.getContents().equals(newPost.getContents())))).isEqualTo(true);
        assertThat(id == newPost.getId()).isTrue();
    }

    @Test
    void findAllTest() {
        PostModel postModel = new PostModel("writer1", "hello", "world");
        Post post = new Post(postModel);
        PostModel postModel2 = new PostModel("writer1", "hello2", "myworld");
        Post post2 = new Post(postModel2);
        postRepository.insert(post);
        postRepository.insert(post2);
        Posts result = postRepository.findAll();
        assertEquals(result.size(), 2);
    }

}
