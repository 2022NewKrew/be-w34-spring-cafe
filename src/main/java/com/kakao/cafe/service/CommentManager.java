package com.kakao.cafe.service;

import com.kakao.cafe.entity.Comment;
import com.kakao.cafe.dto.CommentDto;
import com.kakao.cafe.repo.CommentRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
public class CommentManager implements CommentService {
    private final CommentRepository commentRepository;

    CommentManager(final CommentRepository commentRepository) {
        this.commentRepository = Objects.requireNonNull(commentRepository);
    }

    @Transactional
    @Override
    public void add(@NonNull final CommentDto commentDto) {
        boolean result = commentRepository.add(new Comment(
                commentDto.getUserId(),
                commentDto.getArticleIdx(),
                commentDto.getBody()
        ));

        if (!result) {
            throw new RuntimeException("Failed to insert comment!");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommentDto> getDtoList(final long articleIdx, final String currentUserId) {
        final List<CommentDto> list = commentRepository.getDtoList(articleIdx);
        for (CommentDto commentDto : list) {
            commentDto.setOwner(commentDto.getUserId().equals(currentUserId));
        }
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public CommentDto getDto(final long idx) throws NoSuchElementException {
        final Optional<CommentDto> optional = Optional.ofNullable(commentRepository.getDto(idx));
        if (optional.isEmpty()) {
            throw new NoSuchElementException("Not found comment - " + idx);
        }

        return optional.get();
    }

    @Transactional
    @Override
    public boolean update(@NonNull final CommentDto commentDto) {
        final long idx = commentDto.getIdx();

        try {
            getDto(idx);
        } catch (NoSuchElementException e) {
            return false;
        }

        return commentRepository.update(
                idx,
                new Comment("dummyid", commentDto.getArticleIdx(), commentDto.getBody())
        );
    }

    @Transactional
    @Override
    public boolean delete(@NonNull final long idx) {
        try {
            getDto(idx);
        } catch (NoSuchElementException e) {
            return false;
        }

        return commentRepository.delete(idx);
    }
}
