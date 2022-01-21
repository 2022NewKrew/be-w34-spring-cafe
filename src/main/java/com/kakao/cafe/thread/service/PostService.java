package com.kakao.cafe.thread.service;

import com.kakao.cafe.exception.CommentNotFoundException;
import com.kakao.cafe.exception.PostNotFoundException;
import com.kakao.cafe.exception.UnauthorizedAccessException;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.thread.domain.Comment;
import com.kakao.cafe.thread.domain.Post;
import com.kakao.cafe.thread.domain.Thread;
import com.kakao.cafe.thread.domain.ThreadStatus;
import com.kakao.cafe.thread.dto.*;
import com.kakao.cafe.thread.repository.CommentRepository;
import com.kakao.cafe.thread.repository.PostRepository;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserView;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private PostDetailView toPostDetailView(Post post) {

        User user = userRepository.getById(post.getAuthorId()).orElseThrow(UserNotFoundException::new);
        List<Comment> comments = commentRepository.getCommentsForPost(post.getId());
        return PostDetailView.builder()
                             .id(post.getId())
                             .author(new UserView(user.getUsername(), user.getEmail(), user.getDisplayName()))
                             .title(post.getTitle())
                             .content(post.getContent())
                             .commentCount(comments.size())
                             .comments(comments.stream().map(this::toCommentView).collect(Collectors.toList()))
                             .createdAt(post.getCreatedAt().format(formatter))
                             .lastModifiedAt(post.getLastModifiedAt().format(formatter))
                             .build();
    }

    private CommentView toCommentView(Comment comment) {
        User user = userRepository.getById(comment.getAuthorId()).orElseThrow(UserNotFoundException::new);
        return CommentView.builder()
                          .id(comment.getId())
                          .author(new UserView(user.getUsername(), user.getEmail(), user.getDisplayName()))
                          .content(comment.getContent())
                          .createdAt(comment.getCreatedAt().format(formatter))
                          .lastModifiedAt(comment.getLastModifiedAt().format(formatter))
                          .build();
    }

    public Long addFromForm(Long authorId, PostCreationForm postCreationForm) {
        return postRepository.add(Post.builder()
                                      .authorId(authorId)
                                      .title(postCreationForm.getTitle())
                                      .content(postCreationForm.getContent())
                                      .status(ThreadStatus.VALID.name())
                                      .build());
    }

    public List<PostDetailView> getAll() {
        return postRepository.getAll().stream().map(this::toPostDetailView).collect(Collectors.toList());
    }

    public PostDetailView get(Long id) {
        return toPostDetailView(postRepository.get(id).orElseThrow(PostNotFoundException::new));
    }

    public void updateFromForm(Long authorId, Long postId, PostCreationForm postCreationForm) {
        Post post = postRepository.get(postId).orElseThrow(PostNotFoundException::new);
        validateUserPermissionOnThread(authorId, post);
        postRepository.update(Post.builder()
                                  .id(postId)
                                  .title(postCreationForm.getTitle())
                                  .content(postCreationForm.getContent())
                                  .status(post.getStatus())
                                  .build());
    }

    public void softDelete(Long authorId, Long postId) {
        Post post = postRepository.get(postId).orElseThrow(PostNotFoundException::new);
        validateUserPermissionOnThread(authorId, post);
        postRepository.update(Post.builder()
                                  .id(postId)
                                  .title(post.getTitle())
                                  .content(post.getContent())
                                  .status(ThreadStatus.DELETED.name())
                                  .build());
    }

    public void addCommentToPost(Long authorId, Long postId, CommentCreationForm commentCreationForm) {
        commentRepository.add(Comment.builder()
                                     .parentId(postId)
                                     .authorId(authorId)
                                     .title("")
                                     .content(commentCreationForm.getContent())
                                     .status(ThreadStatus.VALID.name())
                                     .build());
    }

    public void updateComment(Long authorId, Long commentId, CommentCreationForm commentCreationForm) {
        Comment comment = commentRepository.get(commentId).orElseThrow(CommentNotFoundException::new);
        validateUserPermissionOnThread(authorId, comment);
        commentRepository.update(Comment.builder()
                                        .content(commentCreationForm.getContent())
                                        .status(comment.getStatus())
                                        .build());
    }

    public void softDeleteComment(Long authorId, Long commentId) {
        Comment comment = commentRepository.get(commentId).orElseThrow(CommentNotFoundException::new);
        validateUserPermissionOnThread(authorId, comment);
        commentRepository.update(Comment.builder()
                                        .id(commentId)
                                        .content(comment.getContent())
                                        .status(ThreadStatus.DELETED.name())
                                        .build());
    }

    private void validateUserPermissionOnThread(Long authorId, Thread thread) {
        // The permission check is done here not part of Thread domain
        // More complicated permission would require more knowledge than Thread has access to
        if (!thread.getAuthorId().equals(authorId)) {
            throw new UnauthorizedAccessException();
        }
    }
}
