package com.kakao.cafe.repo;

import com.kakao.cafe.entity.Comment;
import com.kakao.cafe.dto.CommentDto;
import org.springframework.lang.NonNull;

import java.util.List;

public interface CommentRepository {
    boolean add(@NonNull final Comment comment);

    CommentDto getDto(final long idx);

    List<CommentDto> getDtoList(final long articleIdx);

    boolean update(final long idx, @NonNull final Comment comment);

    boolean delete(final long idx);
}
