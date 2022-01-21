package com.kakao.cafe.fake.repository;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FakeCommentRepository implements CommentRepository {

    Map<Integer, Comment> comments = new HashMap<>();
    private Integer maxId;

    public FakeCommentRepository() {
        comments.put(1, new Comment(1, "lucas", "test", 1, LocalDateTime.now()));
        comments.put(2, new Comment(2, "lucas", "test", 1, LocalDateTime.now()));
        maxId = 2;
    }

    @Override
    public void save(Comment comment) {
        if (comments.containsKey(comment.getId())) {
            comments.put(comment.getId(), comment);
            return;
        }
        comments.put(maxId + 1, new Comment(maxId + 1, comment.getWriter(), comment.getContents(), comment.getQnaId(), comment.getCreatedAt()));
        maxId++;
    }

    @Override
    public void batchUpdate(List<Comment> commentList) {
        for (Comment comment : commentList) {
            comments.put(maxId + 1, new Comment(maxId+1, comment.getWriter(), comment.getContents(), comment.getQnaId(), comment.getCreatedAt()));
            maxId++;
        }
    }

    @Override
    public List<Comment> findByQnaIdAndDeleted(Integer qnaId, Boolean isDeleted) {
        return this.comments.values().stream()
                .filter(comment -> comment.getDeleted().equals(false) && comment.getQnaId().equals(qnaId))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Comment> findById(Integer id) {
        try {
            return Optional.of(comments.get(id));
        } catch (NullPointerException nullPointerException) {
            return Optional.empty();
        }
    }
}
