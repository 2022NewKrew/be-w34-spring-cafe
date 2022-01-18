package com.kakao.cafe.service;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.post.AddPostDto;
import com.kakao.cafe.dto.post.PostViewDto;
import com.kakao.cafe.dto.post.SimplePostInfo;
import com.kakao.cafe.dto.post.UpdatePostDto;
import com.kakao.cafe.error.exception.nonexist.PostNotFoundedException;
import com.kakao.cafe.error.exception.nonexist.UserNotFoundedException;
import com.kakao.cafe.error.exception.notmine.PostNotMineException;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.testutil.post.PostDtoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostServiceImpl postService;

    private Post postInRepo;

    private User postWriter;

    @BeforeEach
    void setUp() {
        Long userId = Long.valueOf(1);

        postInRepo = Post.builder()
                .id(Long.valueOf(2))
                .title("Good title")
                .contents("Bad Contents")
                .userId(userId)
                .build();

        postWriter = User.builder()
                .id(userId)
                .email("gallix@kakao.com")
                .password("abcd1234!")
                .nickName("gallix")
                .build();
    }

    @Test
    @DisplayName("Post Id 로 PostViewDto 반환 -> 존재하지 않는 게시글")
    void findPostViewDtoById_nonExist() {
        //Given
        Long nonExistPostId = Long.valueOf(24);
        given(postRepository.findPostViewDtoById(nonExistPostId)).willReturn(Optional.empty());

        //When, Then
        assertThrows(PostNotFoundedException.class, () -> postService.findPostViewDtoById(nonExistPostId));
    }

    @Test
    @DisplayName("Post Id 로 PostViewDto 반환 -> 정상")
    void findPostViewDtoById() {
        //Given
        Long postId = postInRepo.getId();
        PostViewDto postViewDto = PostDtoUtil.createPostViewDto(postId);
        given(postRepository.findPostViewDtoById(postId)).willReturn(Optional.of(postViewDto));

        //When
        PostViewDto result = postService.findPostViewDtoById(postId);

        //Then
        assertEquals(postViewDto, result);
    }

    @Test
    @DisplayName("특정 페이지의 모든 SimplePostInfo 반환 -> 정상")
    void getListOfSimplePostInfo() {
        //Given
        Integer pageNum = 1;
        Integer pageSize = 10;
        List<SimplePostInfo> simplePostInfos = new ArrayList<>();
        given(postRepository.getListOfSimplePostInfo(pageNum, pageSize)).willReturn(simplePostInfos);

        //When
        List<SimplePostInfo> result = postService.getListOfSimplePostInfo(pageNum, pageSize);

        //Then
        assertEquals(simplePostInfos, result);
    }

    @Test
    @DisplayName("게시글 추가 -> 존재하지 않는 유저의 ID")
    void addPost_nonExistUser() {
        //Given
        Long nonExistUserId = Long.valueOf(215);
        given(userRepository.existsById(nonExistUserId)).willReturn(false);

        AddPostDto addPostDto = PostDtoUtil.createAddPostDto();

        //When, Then
        assertThrows(UserNotFoundedException.class, () -> postService.addPost(addPostDto, nonExistUserId));
    }

    @Test
    @DisplayName("게시글 추가 -> 정상, postRepository 확인")
    void addPost_checkRepo() {
        //Given
        Long userId = postWriter.getId();
        given(userRepository.existsById(userId)).willReturn(true);

        AddPostDto addPostDto = PostDtoUtil.createAddPostDto();

        //When
        postService.addPost(addPostDto, userId);

        //Then
        then(postRepository).should(times(1)).save(any(Post.class));
    }

    @Test
    @DisplayName("게시글 추가 -> 정상, return Post 확인")
    void addPost_checkNewPost() {
        //Given
        Long userId = postWriter.getId();
        given(userRepository.existsById(userId)).willReturn(true);

        AddPostDto addPostDto = PostDtoUtil.createAddPostDto();

        //When
        Post result = postService.addPost(addPostDto, userId);

        //Then
        checkNewPostWithAddPostDto(addPostDto, result);
    }

    private void checkNewPostWithAddPostDto(AddPostDto addPostDto, Post newPost) {
        assertEquals(addPostDto.getTitle(), newPost.getTitle());
        assertEquals(addPostDto.getContents(), newPost.getContents());
    }

    @Test
    @DisplayName("게시글 수정 -> 해당 ID의 게시글 존재하지 않음")
    void updatePost_nonExist() {
        //Given
        Long nonExistPostId = Long.valueOf(25);
        UpdatePostDto updatePostDto = PostDtoUtil.createUpdatePostDto(nonExistPostId);
        given(postRepository.findById(nonExistPostId)).willReturn(Optional.empty());

        Long writerId = postWriter.getId();

        //When, Then
        assertThrows(PostNotFoundedException.class, () -> postService.updatePost(updatePostDto, writerId));
        then(postRepository).should(never()).save(any(Post.class));
    }

    @Test
    @DisplayName("게시글 수정 -> 게시글의 작성자 ID와 접속자의 ID 가 일치하지 않음")
    void updatePost_notMine() {
        //Given
        Long postId = postInRepo.getId();
        UpdatePostDto updatePostDto = PostDtoUtil.createUpdatePostDto(postId);
        given(postRepository.findById(postId)).willReturn(Optional.of(postInRepo));

        Long anotherUserId = postWriter.getId() + 12;

        //When, Then
        assertThrows(PostNotMineException.class, () -> postService.updatePost(updatePostDto, anotherUserId));
        then(postRepository).should(never()).save(any(Post.class));
    }

    @Test
    @DisplayName("게시글 수정 -> 정상, 게시글 변화 확인")
    void updatePost() {
        //Given
        Long postId = postInRepo.getId();
        UpdatePostDto updatePostDto = PostDtoUtil.createUpdatePostDto(postId);
        given(postRepository.findById(postId)).willReturn(Optional.of(postInRepo));

        Long writerId = postWriter.getId();

        assertNotEqualUpdatePostDtoAndPostMember(updatePostDto, postInRepo);

        given(postRepository.save(postInRepo)).willReturn(postInRepo);

        //When
        Post result = postService.updatePost(updatePostDto, writerId);

        //Then
        assertEquals(postInRepo, result);
        assertEqualUpdatePostDtoAndPostMember(updatePostDto, result);
    }

    private void assertNotEqualUpdatePostDtoAndPostMember(UpdatePostDto updatePostDto, Post post) {
        assertNotEquals(updatePostDto.getTitle(), post.getTitle());
        assertNotEquals(updatePostDto.getContents(), post.getContents());
    }

    private void assertEqualUpdatePostDtoAndPostMember(UpdatePostDto updatePostDto, Post result) {
        assertEquals(updatePostDto.getTitle(), result.getTitle());
        assertEquals(updatePostDto.getContents(), result.getContents());
    }

    @Test
    @DisplayName("ID에 해당하는 게시글 조회수 추가 -> 정상")
    void increaseViewNumById() {
        //Given
        Long postId = postInRepo.getId();

        //When
        postService.increaseViewNumById(postId);

        //Then
        then(postRepository).should(times(1)).increaseViewNumById(postId);
    }

    @Test
    @DisplayName("총 게시글 개수 반환 -> 정상")
    void countAll() {
        //Given
        int numOfPost = 12455;
        given(postRepository.countAll()).willReturn(numOfPost);

        //When
        int result = postService.countAll();

        //Then
        assertEquals(numOfPost, result);
    }
}