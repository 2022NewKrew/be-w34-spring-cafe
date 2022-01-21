package com.kakao.cafe.post.domain.repository;

import com.kakao.cafe.JdbcRepositoryTest;
import com.kakao.cafe.post.data.PostsData;
import com.kakao.cafe.post.domain.entity.Comment;
import com.kakao.cafe.post.domain.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static org.assertj.core.api.Assertions.*;

class PostRepositoryTest extends JdbcRepositoryTest {

    @Test
    @DisplayName("PostRepository 가져오기 성공")
    void successGetRepository(){
        assertThat(postRepository).isNotNull();
    }

    @Test
    @DisplayName("Post 한 개 가져오기 성공")
    void successGet(){
        //given
        final List<Post> posts = PostsData.getPostList();
        final Post post = posts.get(0);
        postRepository.savePostAll(posts);

        //when
        final Post postSaved = postRepository.getPost(post.getId()).orElseThrow();

        //then
        assertThat(postSaved).isEqualTo(post);
        assertThat(postSaved.getComments()).isEqualTo(post.getComments());
    }

    @Test
    @DisplayName("Post 여러 개 가져오기 성공")
    void successGetAll() {
        //given
        final List<Post> posts = PostsData.getPostList();
        postRepository.savePostAll(posts);

        //when
        final List<Post> postSaved = postRepository.getPosts(0, 4);

        //then
        posts.sort(comparing(Post::getTimeWritten).reversed());
        assertThat(postSaved.size()).isEqualTo(4);
        assertThat(postSaved).isEqualTo(posts.subList(0,4));
    }

    @ParameterizedTest
    @DisplayName("post 저장 성공")
    @MethodSource("com.kakao.cafe.post.data.PostsData#getPostStream")
    void successSave(Post post) {
        //given

        //when
        postRepository.savePost(post);
        final Post actualPost = postRepository.getPost(post.getId()).orElseThrow();

        //then
        assertThat(actualPost).isEqualTo(post);
    }

    @ParameterizedTest
    @DisplayName("comment 추가 성공")
    @MethodSource("com.kakao.cafe.post.data.PostsData#getCommentStream")
    void saveComment(Comment comment) {
        //given
        final Post post = PostsData.getPostList().get(0);
        postRepository.savePost(post);

        //when
        postRepository.saveComment(post.getId(), comment);
        final Post actualPost = postRepository.getPost(post.getId()).orElseThrow();

        //then
        assertThat(actualPost.getComments().size()).isNotEqualTo(post.getComments().size());
        assertThat(actualPost.getComments()).contains(comment);
    }

    @ParameterizedTest
    @DisplayName("Post 내용 업데이트 성공")
    @MethodSource("com.kakao.cafe.post.data.PostsData#getPostStream")
    void successUpdate(Post post){
        //given
        postRepository.savePost(post);
        final String newContent = post.getContent().concat(" good~");

        //when
        postRepository.update(post.getId(), newContent);
        final Post updatedPost = postRepository.getPost(post.getId()).orElseThrow();

        //then
        assertThat(updatedPost.getContent()).isNotEqualTo(post.getContent());
        assertThat(updatedPost.getContent()).isEqualTo(newContent);
    }

    @Test
    @DisplayName("Post 삭제 성공")
    void successDelete(){
        //given
        final List<Post> posts = PostsData.getPostList();
        final Post deletedPost = posts.get(0);
        postRepository.savePostAll(posts);

        //when
        postRepository.softDelete(deletedPost.getId());
        Optional<Post> actualDeleted = postRepository.getPost(deletedPost.getId());
        List<Post> remainedPosts = postRepository.getPosts(0, posts.size()-1);

        //then
        assertThat(actualDeleted.isEmpty()).isEqualTo(true);
        assertThat(remainedPosts.size()).isEqualTo(posts.size()-1);
        assertThat(remainedPosts).doesNotContain(deletedPost);
    }

    @Test
    @DisplayName("Comment 삭제 성공")
    void successDeleteComment(){
        //given
        final Post post = PostsData.getPostList().get(0);
        final Comment commentDeleted = post.getComments().get(0);
        postRepository.savePost(post);

        //when
        postRepository.deleteComment(commentDeleted.getId());
        final Post postUpdated = postRepository.getPost(post.getId()).orElseThrow();

        //then
        assertThat(postUpdated.getComments().size()).isEqualTo(post.getComments().size()-1);
        assertThat(postUpdated.getComments()).doesNotContain(commentDeleted);
    }
}