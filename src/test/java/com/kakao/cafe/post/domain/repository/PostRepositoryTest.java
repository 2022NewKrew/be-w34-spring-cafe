package com.kakao.cafe.post.domain.repository;

import com.kakao.cafe.JdbcRepositoryTest;
import com.kakao.cafe.post.data.PostsData;
import com.kakao.cafe.post.domain.entity.Comment;
import com.kakao.cafe.post.domain.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static org.assertj.core.api.Assertions.*;

class PostRepositoryTest extends JdbcRepositoryTest {

    @Test
    @DisplayName("PostRepository 가져오기 성공")
    void successGetRepository(){
        assertThat(postRepository).isNotNull();
    }

    @Test
    @DisplayName("Post 여러 개 가져오기 성공")
    void successGetAllPosts() {
        //given
        List<Post> posts = PostsData.getPostList();
        postRepository.savePostAll(posts);

        //when
        List<Post> actualPosts = postRepository.getPosts(0, 4);

        //then
        posts.sort(comparing(Post::getTimeWritten).reversed());
        assertThat(actualPosts.size()).isEqualTo(4);
        assertThat(actualPosts).isEqualTo(posts.subList(0,4));
    }

    @ParameterizedTest
    @DisplayName("post 저장 성공")
    @MethodSource("com.kakao.cafe.post.data.PostsData#getPostStream")
    void savePost(Post post) {
        //given

        //when
        postRepository.savePost(post);
        Post actualPost = postRepository.getPost(post.getId()).orElseThrow();

        //then
        assertThat(actualPost).isEqualTo(post);
    }

    @ParameterizedTest
    @DisplayName("comment 추가 성공")
    @MethodSource("com.kakao.cafe.post.data.PostsData#getCommentStream")
    void saveComment(Comment comment) {
        //given
        Post post = PostsData.getPostList().get(0);
        postRepository.savePost(post);

        //when
        postRepository.saveComment(post.getId(), comment);
        Post actualPost = postRepository.getPost(post.getId()).orElseThrow();

        //then
        assertThat(actualPost.getComments().size()).isNotEqualTo(post.getComments().size());
        assertThat(actualPost.getComments()).contains(comment);
    }
}